package hello.itemservice.web.basic;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import hello.itemservice.domain.ItemUpdateDTO;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepo;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepo.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item item = itemRepo.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam int quantity,
                            Model model) {
        Item item = new Item(itemName, price, quantity);
        itemRepo.save(item);

        model.addAttribute("item", item);
        return "basic/item";
    }

    /*
     * @ModelAttribute("item") Item item;
     * -> model.addAttribute("item", item); 자동 추가
     */
//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute("item") Item item) {
        itemRepo.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) {
        itemRepo.save(item);
        return "basic/item";
    }

    @PostMapping("/add")
    public String addItemV4(Item item) {
        itemRepo.save(item);
        return "basic/item";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepo.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute ItemUpdateDTO item) {
        itemRepo.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepo.save(new Item("itemA", 10000, 10));
        itemRepo.save(new Item("itemB", 20000, 20));
    }
}
