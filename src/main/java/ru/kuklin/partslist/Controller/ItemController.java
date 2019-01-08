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
    private int currentPage = 0;

    @Autowired(required = true)
    @Qualifier(value = "itemService")
    public void setItemService(ItemService itemService) {this.itemService = itemService;}

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String listItems(@RequestParam(required = false) Integer page, String sort, Boolean filter, Model model){
        model.addAttribute("item", new Item());
        model.addAttribute("sort", sort);
        model.addAttribute("filter", filter);
        List<Item> items;
        if(filter==null)filter=false;
        logger.info(" filter value : " + filter);
        if(filter) items = this.itemService.getItemByName("", sort, filter);
        else items = this.itemService.listItem(sort);
        canCollect(items, model);
        setPaging(page, model, items);
        return "item";
    }

    @RequestMapping(value = "item/search")
    public String searchItem(@RequestParam(required = false) Integer page, @RequestParam(required = false) String searchItem, String sort, Boolean filter, Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("sort", sort);
        model.addAttribute("searchItem", searchItem);
        model.addAttribute("filter", filter);
        if(filter==null)filter=false;
        List<Item> listSearch;
        logger.info(" " + filter);
        if(filter)listSearch = this.itemService.getItemByName(searchItem, sort, true);
        else listSearch = this.itemService.getItemByName(searchItem, sort);
        setPaging(page, model, listSearch);
        return "item";
    }

    @RequestMapping(value = "/item/add", method = RequestMethod.POST)
    public String addItem(@ModelAttribute("item") Item item){
        logger.info(item.toString());
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
        //model.addAttribute("listItems", this.itemService.listItem(sortType));
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
            if(i.getReq() && i.getQty()<result)result=i.getQty();
        }
        if(result<0)result=0;
        model.addAttribute("counter", result);
    }
}
