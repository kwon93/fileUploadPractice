package upload.example.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import upload.example.domain.Item;
import upload.example.domain.ItemRepository;
import upload.example.service.file.FileStore;
import upload.example.service.ItemService;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemResponseDTO responseDTO){
        return "item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemRequestDTO requestDTO,
                           RedirectAttributes redirectAttributes) throws IOException {

        //데이터 베이스에 저장.
        ItemResponseDTO responseDTO = itemService.saveFiles(requestDTO);
        redirectAttributes.addAttribute("itemId", responseDTO.getItemId());
        return "redirect:/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model){
        Item item = itemService.findByItemId(id);
        model.addAttribute("item",item);

        return "item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws IOException {
        return itemService.fileResource(filename);
    }
}
