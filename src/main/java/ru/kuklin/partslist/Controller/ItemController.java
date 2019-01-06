package ru.kuklin.partslist.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.kuklin.partslist.model.Item;
import ru.kuklin.partslist.service.ItemService;

import java.util.List;

@Controller
public class ItemController {
    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
    private ItemService itemService;
    private String errorDB = null;
    private String searchString = null;
    private String sortType = "name";
    private int currentPage = 0;

    @Autowired(required = true)
    @Qualifier(value = "itemService")
    public void setItemService(ItemService itemService) {this.itemService = itemService;}

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String listItems(@RequestParam(required = false) Integer page, String sort, Model model){
        if(sort!=null)sortType=sort;
        model.addAttribute("item", new Item());
        model.addAttribute("error", errorDB);
        errorDB = null;
        List<Item> items = this.itemService.listItem(sortType);
        canCollect(items, model);
        setPaging(page, model, items);
//        logger.info("********************************** return items **********************************");
        return "item";
    }

    @RequestMapping(value = "item/search")
    public String searchItem(@RequestParam(required = false) Integer page, @RequestParam(required = false) String searchItem, String sort, Model model) {
        model.addAttribute("item", new Item());
        String search;
        if(sort!=null)sortType=sort;
        if(searchItem!=null){
            search=searchItem;
            searchString=searchItem;
        }
        else if(searchString!=null)search=searchString;
        else return "redirect:/items";

        List<Item> listSearch = this.itemService.getItemByName(search, sortType);
        setPaging(page, model, listSearch);
        return "item";
    }

    @RequestMapping(value = "/item/add", method = RequestMethod.POST)
    public String addItem(@ModelAttribute("item") Item item){
        if(item.getId() == 0)
            this.itemService.addItem(item);
        else
            this.itemService.updateItem(item);
        return "redirect:/items";
    }

    @RequestMapping("/remove/{id}")
    public String removeItem(@PathVariable("id") int id){
        this.itemService.removeItem(id);
        return "redirect:/items";
    }

    @RequestMapping("/edit/{id}")
    public String editItem(@PathVariable("id") int id, Model model){
        model.addAttribute("item", this.itemService.getItemById(id));
        model.addAttribute("listItems", this.itemService.listItem(sortType));
        return "item";
    }

    private void setPaging(Integer page, Model model, List<Item> items) {
        PagedListHolder<Item> pagedListHolder = new PagedListHolder<Item>(items);
        pagedListHolder.setPageSize(10);

        model.addAttribute("maxPages", pagedListHolder.getPageCount());

        if(page == null || page<1 || page> pagedListHolder.getPageCount())
            page=1;

        model.addAttribute("page", page);
        currentPage = page;

        pagedListHolder.setPage(page-1);
        model.addAttribute("listItem", pagedListHolder.getPageList());
    }

    public void canCollect(List<Item> list, Model model) {
        int result = Integer.MAX_VALUE;
        for(Item i:list){
            if(i.getReq()==true && i.getQty()<result)result=i.getQty();
        }
        if(result<0)result=0;
        model.addAttribute("counter", result);
    }
}
