package upload.example.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class ItemRequestDTO {

    private Long itemId;
    private String itemName;
    private MultipartFile attachFile;

    public ItemRequestDTO() {
    }
}
