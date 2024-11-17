package ru.andreyszdlv.springbootstarters3loadimage.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import ru.andreyszdlv.springbootstarters3loadimage.exception.DeleteImageException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.EmptyImageException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.FileDeleteException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.FileIsNotImageException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.FileUploadException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.ImageUploadException;
import ru.andreyszdlv.springbootstarters3loadimage.exception.NoSuchImageException;
import ru.andreyszdlv.springbootstarters3loadimage.service.ImageService;
import ru.andreyszdlv.springbootstarters3loadimage.service.S3Service;
import ru.andreyszdlv.springbootstarters3loadimage.util.ImageUtils;

@RequiredArgsConstructor
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final S3Service s3Service;

    public String uploadImage(MultipartFile image)
            throws EmptyImageException, ImageUploadException {
        log.info("Executing uploadImage");

        log.info("Validating image");
        validateImage(image);

        log.info("Saving new image");
        return this.saveNewImage(image);
    }

    public String getImageUrlByImageId(String imageId) throws NoSuchImageException {
        log.info("Executing getImageUrlById for imageId: {}", imageId);

        try {
            log.info("Creating image url for imageId: {}", imageId);
            return s3Service.getFileUrlById(imageId);
        } catch (Exception e) {
            log.error("Error: no such image", e);
            throw new NoSuchImageException("errors.404.image_not_found");
        }
    }

    public void deleteImageById(String imageId) throws DeleteImageException {
        log.info("Executing deleteImageById for imageId: {}", imageId);

        log.info("Deleting image for imageId: {}", imageId);
        try {
            s3Service.deleteFileById(imageId);
        }
        catch (FileDeleteException e){
            throw new DeleteImageException("errors.500.image_delete_failed");
        }
    }

    private void validateImage(MultipartFile avatar) throws EmptyImageException {
        log.info("Checking the image for emptiness");
        if (avatar.isEmpty() || avatar.getOriginalFilename() == null) {
            log.error("Error: image is empty");
            throw new EmptyImageException("errors.400.image_is_empty");
        }

        log.info("Checking file is an image");
        if(!ImageUtils.isImageFile(avatar)){
            log.error("File is not an image");
            throw new FileIsNotImageException("errors.400.file_is_not_image");
        }
        log.info("File is an image");
    }

    private String saveNewImage(MultipartFile avatar) throws ImageUploadException {
        log.info("Executing saveNewImage");

        log.info("Getting bytes from image");
        byte[] avatarBytes = this.getImageBytes(avatar);

        log.info("Generating imageId");
        String imageId = ImageUtils.generateImageId(avatar);

        try{
            log.info("Saving image for imageId: {}", imageId);
            s3Service.saveFile(avatarBytes, imageId);
        }
        catch(FileUploadException e){
            log.error("Error saving image: {}", e.getMessage());
            throw new ImageUploadException("errors.400.image_upload_failed");
        }

        return imageId;
    }

    private byte[] getImageBytes(MultipartFile avatar) throws ImageUploadException {
        log.info("Executing getImageBytes");
        try {
            return avatar.getBytes();
        } catch (Exception e) {
            log.error("Error get byte[]: {}", e.getMessage());
            throw new ImageUploadException("errors.400.image_upload_failed");
        }
    }
}
