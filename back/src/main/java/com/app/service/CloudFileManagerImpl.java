//package com.app.service;

//import com.sun.tools.javac.file.BaseFileManager;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ObjectUtils;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.annotation.PostConstruct;
//
///**
// * @author Hincu Andrei (andreih1981@gmail.com)on 03.06.2019.
// * @version $Id$.
// * @since 0.1.
// */
//@Service
//@Qualifier(BaseFileManager.CLOUD_FILE_MANAGER)
//public class CloudFileManagerImpl extends  {
//    private Cloudinary cloudinary;
//​
//    @Value(CLOUD_API_NAME)
//    private String cloud_name;
//    @Value(CLOUD_API_KEY)
//    private String api_key;
//    @Value(CLOUD_API_SECRET)
//    private String api_secret;
//​
//    @PostConstruct
//    private void initCloudinary() {
//        cloudinary = new Cloudinary(ObjectUtils.asMap(
//                CLOUD_NAME, cloud_name,
//                API_KEY, api_key,
//                API_SECRET, api_secret));
//    }
//​
//    @Override
//    public Map saveImage(MultipartFile file, String folder) {
//        try {
//            Map params = ObjectUtils.asMap(PUBLIC_ID, folder + DASH + cloudinary.randomPublicId());
//            return cloudinary.uploader().upload(file.getBytes(), params);
//        } catch (IOException ex) {
//            throw new UploadFileException(UPLOAD_EXCEPTION + file.getOriginalFilename());
//        }
//    }
//​
//    @Override
//    public void removeImage(String public_id) {
//        try {
//            cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());
//        } catch (IOException ex) {
//            throw new RemoveFileException(REMOVE_EXCEPTION + public_id);
//        }
//    }

//}
