package upload.example.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import upload.example.controller.ItemRequestDTO;
import upload.example.controller.ItemResponseDTO;
import upload.example.domain.Item;
import upload.example.domain.ItemRepository;
import upload.example.domain.UploadFile;
import upload.example.service.file.FileStore;

import java.io.IOException;
import java.net.MalformedURLException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final FileStore fileStore;

    public ItemResponseDTO saveFiles(ItemRequestDTO request) throws IOException {
        UploadFile attachFile = fileStore.storeFile(request.getAttachFile());
        Item itemFromAttachFileAndRequest = Item.of(attachFile, request);
        Item savedItem = itemRepository.save(itemFromAttachFileAndRequest);
        return ItemResponseDTO.of(savedItem);
    }

    public Item findByItemId(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 아이디 입니다."));
    }

    public UrlResource fileResource(String filename) throws IOException {
        return new UrlResource("file:"+ fileStore.getFullPath(filename));
    }
}
