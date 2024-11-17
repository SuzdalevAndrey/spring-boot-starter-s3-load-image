package ru.andreyszdlv.springbootstarters3loadimage.service;

import org.springframework.web.multipart.MultipartFile;
import ru.andreyszdlv.springbootstarters3loadimage.exception.DeleteImageException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.EmptyImageException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.ImageUploadException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.NoSuchImageException;

public interface ImageService {
    String uploadImage(MultipartFile image) throws EmptyImageException, ImageUploadException;

    String getImageUrlByImageId(String imageId) throws NoSuchImageException;

    void deleteImageById(String imageId) throws DeleteImageException;
}
