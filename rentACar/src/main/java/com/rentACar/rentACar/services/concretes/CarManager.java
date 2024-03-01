package com.rentACar.rentACar.services.concretes;

import com.rentACar.rentACar.core.services.CloudinaryService;
import com.rentACar.rentACar.core.utilities.mappers.services.ModelMapperService;
import com.rentACar.rentACar.core.utilities.results.DataResult;
import com.rentACar.rentACar.core.utilities.results.Result;
import com.rentACar.rentACar.core.utilities.results.SuccessDataResult;
import com.rentACar.rentACar.core.utilities.results.SuccessResult;
import com.rentACar.rentACar.entities.concretes.Car;
import com.rentACar.rentACar.entities.concretes.CarStatus;
import com.rentACar.rentACar.entities.concretes.Comment;
import com.rentACar.rentACar.entities.concretes.Discount;
import com.rentACar.rentACar.repositories.CarRepository;
import com.rentACar.rentACar.services.abstracts.CarService;
import com.rentACar.rentACar.services.abstracts.DiscountService;
import com.rentACar.rentACar.services.constants.Messages;
import com.rentACar.rentACar.services.dtos.requests.Car.AddCarRequest;
import com.rentACar.rentACar.services.dtos.requests.Car.UpdateCarRequest;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarIdCommentResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarListResponse;
import com.rentACar.rentACar.services.dtos.responses.Car.GetCarResponse;
import com.rentACar.rentACar.services.rules.CarBusinessRules;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {
    private final CarRepository carRepository;
    private final ModelMapperService modelMapperService;
    private final CarBusinessRules carBusinessRules;
    private final CloudinaryService cloudinaryService;
    private final DiscountService discountService;



    @Override
    public DataResult<List<GetCarListResponse>> getAll() {
        List<Car> cars = carRepository.findByDeletedFalse();
        List<GetCarListResponse> responses = cars.stream().map(car -> modelMapperService.forResponse()
                .map(car,GetCarListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    public DataResult<List<GetCarListResponse>> getAllActiveCar(){
        List<Car> cars = carRepository.findByDeletedFalseAndCarStatus(CarStatus.ACTIVE);
        List<GetCarListResponse> responses = cars.stream().map(car -> modelMapperService.forResponse()
                .map(car,GetCarListResponse.class)).collect(Collectors.toList());
        return new SuccessDataResult<>(responses);
    }

    @Override
    public DataResult<GetCarResponse> getById(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        carBusinessRules.checkIfCarStatus(car.getId());
        GetCarResponse response = this.modelMapperService.forResponse().map(car,GetCarResponse.class);
        return new SuccessDataResult<>(response);
    }

    @Override
    public Result add(AddCarRequest request) {
        request.setPlate(request.getPlate().replaceAll("[^a-zA-Z0-9]", ""));
        this.carBusinessRules.checkIfPlateFormat(request.getPlate());
        this.carBusinessRules.checkIfPlateExists(request.getPlate());
        this.carBusinessRules.checkIfModelIdZero(request.getModelId());
        this.carBusinessRules.checkIfColorIdZero(request.getColorId());
        this.carBusinessRules.checkIfModelId(request.getModelId());
        this.carBusinessRules.checkIfColorId(request.getColorId());

        Car car = this.modelMapperService.forRequest().map(request,Car.class);
        car.setImagePath(cloudinaryService.uploadFile(request.getFile()));
        car.setDeleted(false);
        carRepository.save(car);
        return new SuccessResult(Messages.ADDED_CAR);
    }

    @Override
    public Result update(UpdateCarRequest request) {
        request.setPlate(request.getPlate().replaceAll("[^a-zA-Z0-9]", ""));

        this.carBusinessRules.checkIfPlateFormat(request.getPlate());
        this.carBusinessRules.checkIfPlateExists(request.getPlate());
        this.carBusinessRules.checkIfModelIdZero(request.getModelId());
        this.carBusinessRules.checkIfColorIdZero(request.getColorId());
        this.carBusinessRules.checkIfModelId(request.getModelId());
        this.carBusinessRules.checkIfColorId(request.getColorId());

        Car car = this.modelMapperService.forRequest().map(request,Car.class);
        car.setImagePath(cloudinaryService.uploadFile(request.getFile()));
        car.setDeleted(false);
        carRepository.save(car);
        return new SuccessResult(Messages.UPDATED_CAR);
    }

    @Override
    public Result delete(int id) {
        Car carToDelete = carRepository.findById(id).orElseThrow();
        carBusinessRules.deletedRental(carToDelete);
        carRepository.save(carToDelete);
        return new SuccessResult(Messages.DELETED_CAR);
    }

    @Override
    public boolean controlCarId(int id) {
        try {
            Car car = carRepository.findById(id).orElseThrow();
            return true;
        }catch (NoSuchElementException e){
            return false;
        }
    }

    @Override
    public int carKilometer(int id) {
       Car car = carRepository.findById(id).orElseThrow();
        return car.getKilometer();
    }

    @Override
    public Float carDailyPrice(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        return car.getDailyPrice();
    }



    @Override
    public Float carTaxRate(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        return car.getTaxRate().getRate();
    }

    @Override
    public String carStatus(int id) {
        Car car = carRepository.findById(id).orElseThrow();
        return car.getCarStatus().name();
    }


   /* public Car rateCar(int carId, short minFindeksRate) {
        Car car = carRepository.findById(carId).orElseThrow(()->new RuntimeException(Messages.CHECK_IF_CAR_ID));
        short currentRating  = car.getMinFindeksRate();
        int numOfRaiting = carRepository.countRatings(carId);
        short newRating = (short) ((currentRating * numOfRaiting + minFindeksRate)/(numOfRaiting+1));
        car.setMinFindeksRate(newRating);
        return carRepository.save(car);
    }*/

    public List<GetCarIdCommentResponse> getComment(int id){
        Car car = carRepository.findById(id).orElseThrow();
        List<Comment> comments = car.getComments();
        List<GetCarIdCommentResponse> responses = comments.stream().map(comment -> modelMapperService.forResponse().map(comment,GetCarIdCommentResponse.class))
                .collect(Collectors.toList());
        return responses;
    }

    public void carStatusActive(int id){
        Car car = carRepository.findById(id).orElseThrow();
        car.setCarStatus(CarStatus.ACTIVE);
    }
    public void carStatusPasive(int id){
        Car car = carRepository.findById(id).orElseThrow();
        car.setCarStatus(CarStatus.PASIVE);
    }
    public void startKilometer(int id,int startKilometer){
        Car car = carRepository.findById(id).orElseThrow();
        car.setKilometer(startKilometer);
    }

    public List<GetCarListResponse> getDiscountedCars() {
        List<Car> discountedCars = carRepository.findByDeletedFalseAndCarStatus(CarStatus.ACTIVE);
        List<Discount> discounts = discountService.getAllDiscount();

        List<Car> discountedCarsWithDiscount = new ArrayList<>();

        for (Discount discount : discounts) {
            Car car = discount.getCar();
            if (discountedCars.contains(car)) { // İndirimli araçlar listesinde varsa
                discountedCarsWithDiscount.add(car);
            }
        }

        List<GetCarListResponse> responses = discountedCarsWithDiscount.stream()
                .map(car -> modelMapperService.forResponse().map(car, GetCarListResponse.class))
                .collect(Collectors.toList());

        return responses;
    }

}