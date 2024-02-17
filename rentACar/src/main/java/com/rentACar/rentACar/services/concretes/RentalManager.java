package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.configurations.DiscountConfiguration;
import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.entities.concretes.Invoice;
import com.rentACar.rentACar.entities.concretes.Rental;
import com.rentACar.rentACar.repositories.RentalRepository;
import com.rentACar.rentACar.services.abstracts.*;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Discount.AddUserDiscountRequest;
import com.rentACar.rentACar.services.dtos.requests.Invoice.AddInvoiceRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.AddRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.CarFilterRequest;
import com.rentACar.rentACar.services.dtos.requests.Rental.UpdateRentalRequest;
import com.rentACar.rentACar.services.dtos.requests.User.UpdateUserRequest;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetCarFilterResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalListResponse;
import com.rentACar.rentACar.services.dtos.responses.Rental.GetRentalResponse;
import com.rentACar.rentACar.services.rules.DiscountBusinessRules;
import com.rentACar.rentACar.services.rules.RentalBusinessRules;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RentalManager implements RentalService {
    private final RentalRepository rentalRepository;
    private final ModelMapperService modelMapperService;
    private final CarService carService;
    private final InvoiceService invoiceService;
    private final UserService userService;
    private final DiscountBusinessRules discountBusinessRules;
    private final DiscountService discountService;
    private DiscountConfiguration discountConfig;
    private final RentalBusinessRules rentalBusinessRules;




    @Override
    public DataResult<List<GetRentalListResponse>> getAll() {
        List<Rental> rentals = rentalRepository.findAll();
        List<GetRentalListResponse> responses = rentals.stream().map(rental -> modelMapperService.forResponse()
                .map(rental, GetRentalListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetRentalResponse> getById(int id) {
        Rental rental = rentalRepository.findById(id).orElseThrow();
        GetRentalResponse response = this.modelMapperService.forResponse().map(rental, GetRentalResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public DataResult<GetRentalResponse> add(AddRentalRequest request) {
            Long totalDay = ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate());
            this.rentalBusinessRules.checkIfEndDateBeforeStartDate(request.getStartDate(),request.getEndDate());
            this.rentalBusinessRules.checkIfCarId(request.getCarId());
            this.rentalBusinessRules.check25Day(request.getStartDate(),request.getEndDate());
            Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
            rental.setStartKilometer(carService.carKilometer(request.getCarId()));
            rental.setUser(userService.userEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
            int carId = request.getCarId();
            String code = request.getDiscountCode();
            Float discount = discountBusinessRules.discount(carId,code);
            rentalRepository.save(rental);

            AddUserDiscountRequest userDiscountRequest = new AddUserDiscountRequest();
            userDiscountRequest.setRental(rental);
            userDiscountRequest.setDiscount(discount);
            userDiscount(userDiscountRequest);

            AddInvoiceRequest invoiceRequest = new AddInvoiceRequest();
            Float taxRate = carService.carTaxRate(request.getCarId());
            Float dailyPrice = carService.carDailyPrice(request.getCarId());
            invoiceRequest.setRental(rental);
            invoiceRequest.setTaxRate(taxRate);
            invoiceRequest.setDiscountRate(discount);
            invoiceRequest.setTotalDay(totalDay);
            invoiceRequest.setDailyPrice(dailyPrice);
            invoiceService.add(invoiceRequest);

            GetRentalResponse response = modelMapperService.forResponse().map(rental,GetRentalResponse.class);
            response.setDiscount(discount);
            response.setDiscountCode(request.getDiscountCode());
            return new SuccessDataResult<>(response);
    }

    @Override
    public Result update(UpdateRentalRequest request) {
        Long totalDay = ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate());
        this.rentalBusinessRules.checkIfEndDateBeforeStartDate(request.getStartDate(),request.getEndDate());
        this.rentalBusinessRules.checkIfCarId(request.getCarId());
        this.rentalBusinessRules.check25Day(request.getStartDate(),request.getEndDate());
        Rental rental = this.modelMapperService.forRequest().map(request, Rental.class);
        rental.setStartKilometer(carService.carKilometer(request.getCarId()));
        rental.setUser(userService.userEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        rentalRepository.save(rental);

        AddInvoiceRequest invoiceRequest = new AddInvoiceRequest();
        Float taxRate = carService.carTaxRate(request.getCarId());
        Float discount = discountBusinessRules.discount(request.getCarId(),request.getDiscountCode());
        Float dailyPrice = carService.carDailyPrice(request.getCarId());
        invoiceRequest.setRental(rental);
        invoiceRequest.setTaxRate(taxRate);
        invoiceRequest.setDiscountRate(discount);
        invoiceRequest.setTotalDay(totalDay);
        invoiceRequest.setDailyPrice(dailyPrice);
        invoiceService.add(invoiceRequest);
        return new SuccessResult(Messages.UPDATED_RENTAL);
    }

    @Override
    @Transactional
    public Result delete(int id) {
        Rental rentalToDelete = rentalRepository.findById(id).orElseThrow();
        rentalRepository.delete(rentalToDelete);
        return new SuccessResult(Messages.DELETED_RENTAL);
    }

    public boolean controlRentalId(int id) {
        try {
            Rental rental=rentalRepository.findById(id).orElseThrow();
            return true;
        } catch (NoSuchFieldError e) {
            return false;
        }
    }


    //Filreleme yapıldığında kullanılıyor
    @Override
    public GetCarFilterResponse carFilter(CarFilterRequest request) {
        Long totalDay = ChronoUnit.DAYS.between(request.getStartDate(),request.getEndDate());
        Float price = carService.carDailyPrice(request.getCarId())*totalDay;
        int userId =userService.userId(SecurityContextHolder.getContext().getAuthentication().getName());
        Float discount = discountBusinessRules.discount(request.getCarId(),request.getDiscountCode());
        Float taxRate = carService.carTaxRate(request.getCarId());
        Float discountedPrice = price - (price*discount);
        Float taxAmount = discountedPrice*taxRate;
        Float totalPrice = discountedPrice + taxAmount;
        String carStatus = carService.carStatus(request.getCarId());
        GetCarFilterResponse response = this.modelMapperService.forRequest().map(request,GetCarFilterResponse.class);
        response.setCarStatus(carStatus);
        response.setTotalPrice(totalPrice);
        response.setDiscount(discount);
        return response;
    }


    @Override
    public void userDiscount(AddUserDiscountRequest request) {

        if(request.getDiscount() == 0){
            int count = countRentalsByUserId(request.getRental().getUser().getId());
            int countUser =  count -1;
            if(countUser % discountConfig.getRentalCount() == 0) {
                AddDiscountRequest discountRequest = new AddDiscountRequest();
                discountRequest.setRate(discountConfig.getRate());
                discountRequest.setCode(generateRandomDiscountCode());
                LocalDate startDate = LocalDate.now();
                discountRequest.setStartDate(startDate);
                LocalDate endDate = startDate.plus(1, ChronoUnit.MONTHS);
                discountRequest.setEndDate(endDate);
                discountRequest.setName(discountConfig.getName());
                discountRequest.setUserId(request.getRental().getUser().getId());
                discountService.add(discountRequest);
            }
        }
    }

    private String generateRandomDiscountCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder code = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int randomIndex = random.nextInt(characters.length());
            code.append(characters.charAt(randomIndex));
        }

        return code.toString();
    }
    public int countRentalsByUserId(int id) {

        return rentalRepository.countByUserId(id);
    }


}