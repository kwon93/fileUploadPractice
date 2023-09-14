package upload.example.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import upload.example.controller.ItemRequestDTO;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String itemName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="upload_file_id")
    private UploadFile attachFile;
//    private List<UploadFile> imageFiles;


    @Builder
    public Item(String itemName, UploadFile attachFile) {
        this.itemName = itemName;
        this.attachFile = attachFile;
    }

    public static Item of(UploadFile uploadFile, ItemRequestDTO request){
        return Item.builder()
                .itemName(request.getItemName())
                .attachFile(uploadFile)
                .build();
    }


}
