package com.nexus.services.impl;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.nexus.helpers.AppConstants;
import com.nexus.services.imageService;

@Service
public class imageServiceImp implements imageService {

    private final Cloudinary cloudinary;

    public imageServiceImp(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadImage(MultipartFile contactImage, String filename) {
        try {
            byte[] data = contactImage.getBytes();
            // Create the map using Collections.singletonMap
            Map<String, Object> options = Collections.singletonMap("public_id", filename);
            cloudinary.uploader().upload(data, options);
            return getUrlFromPublicId(filename);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getUrlFromPublicId(String publicId) {
        return cloudinary.url()
                .transformation(new Transformation()
                        .width(AppConstants.CONTACT_IMAGE_WIDTH)
                        .height(AppConstants.CONTACT_IMAGE_HEIGHT)
                        .crop(AppConstants.CONTACT_IMAGE_CROP))
                .generate(publicId);
    }
}
