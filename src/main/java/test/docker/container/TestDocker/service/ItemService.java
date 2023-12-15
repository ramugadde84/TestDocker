package test.docker.container.TestDocker.service;

import org.springframework.stereotype.Service;
import test.docker.container.TestDocker.model.Comment;
import test.docker.container.TestDocker.model.Item;
import test.docker.container.TestDocker.model.ItemDetails;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {


    public List<Item> formItems() {
        return Arrays.asList(new Item(1,"colgate"),new Item(2,"Mobiles"),
                new Item(3,"Printer"));
    }


    public List<ItemDetails> formItemDetailsList() {
        return Arrays.asList(new ItemDetails(1,1,"Colgate paste is good for Teeth")
        ,new ItemDetails(2,2,"Best Mobile is Apple"),
                new ItemDetails(3,3,"Printer prints the papter"));
    }


    public List<Comment> formComments() {
        return Arrays.asList(new Comment(1,1,"Colgate paste is good and i like this paste"),
                new Comment(2,1,"I am staying in india i like colgate paster"));
    }



    public ItemDetails fetchItemDetailsList(Integer itemId) {
        return formItemDetailsList().stream().filter(itemDetail -> itemDetail.getItemId().equals(itemId))
                .collect(Collectors.toList()).getFirst();
    }


    public List<Comment> fetchCommentsList(Integer itemId) {
        return  formComments().stream().filter(comment -> comment.getItemId().equals(itemId))
                .collect(Collectors.toList());
    }
 }
