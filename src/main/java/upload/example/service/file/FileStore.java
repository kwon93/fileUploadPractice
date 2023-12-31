package upload.example.service.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import upload.example.domain.UploadFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * 서버에 파일을 저장하는 역할을 맡는 객체.
 */
@Component
public class FileStore{
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir + filename;
    }

//    public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles)throws IOException{
//        List<UploadFile> storeFileResult = new ArrayList<>();
//        for (MultipartFile multipartFile : multipartFiles) {
//            if (!multipartFile.isEmpty())  {
//                storeFileResult.add(storeFile(multipartFile));
//            }
//        }
//        return storeFileResult;
//    }
    public UploadFile storeFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return  null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        //image.png

        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return new UploadFile(originalFilename, storeFileName);
    }

    private String  createStoreFileName(String originalFilename) {
        //서버에 저장하는 파일명. -> UUID
        String uuid = UUID.randomUUID().toString();
        //파일 확장자만 추출.
        String ext = extractExt(originalFilename);
        //qwer-1234-12tw21-7454.png
        return uuid + "." + ext;
    }

    private String extractExt(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        return originalFilename.substring(pos + 1);
    }


}
