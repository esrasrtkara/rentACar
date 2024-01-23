package com.rentACar.rentACar.core.services;

import com.cloudinary.Cloudinary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CloudinaryService {
    private Cloudinary cloudinary;

    public String uploadFile(MultipartFile file) {
        try{

            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(),Map.of("public_id", UUID.randomUUID().toString()));
            String publicId = (String) uploadedFile.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
