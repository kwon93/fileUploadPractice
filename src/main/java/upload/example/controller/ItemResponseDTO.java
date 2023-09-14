package upload.example.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import upload.example.domain.Item;

import java.util.List;

@Getter
@NoArgsConstructor
public class ItemResponseDTO {

    private Long itemId;
    private String itemName;

    @Builder
    public ItemResponseDTO(Long itemId, String itemName) {
        this.itemId = itemId;
        this.itemName = itemName;
    }

    public static ItemResponseDTO of(Item item){
        return ItemResponseDTO.builder()
                .itemId(item.getId())
                .itemName(item.getItemName())
                .build();
    }
}
