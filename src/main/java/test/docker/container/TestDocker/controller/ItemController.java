package test.docker.container.TestDocker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.docker.container.TestDocker.model.Comment;
import test.docker.container.TestDocker.model.Item;
import test.docker.container.TestDocker.model.ItemDetails;
import test.docker.container.TestDocker.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/list")
    public List<Item> fetchItemsList() {
        return itemService.formItems();
    }

    @GetMapping("/itemDetails")
    public ItemDetails fetchItemDetailsList(@RequestParam("itemId") Integer itemId) {
        return itemService.fetchItemDetailsList(itemId);
    }


    @GetMapping("/comments")
    public List<Comment> fetchCommentsList(@RequestParam("itemId") Integer itemId) {
        return itemService.fetchCommentsList(itemId);
    }
}
