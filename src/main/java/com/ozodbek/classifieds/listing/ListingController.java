package com.ozodbek.classifieds.listing;

import com.ozodbek.classifieds.listing.dto.CreateListingRequest;
import com.ozodbek.classifieds.listing.dto.ListingResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listings")
public class ListingController {

    private final ListingService listingService;

    public ListingController(ListingService listingService) {
        this.listingService = listingService;
    }

    @PostMapping
    public ListingResponse create(@RequestBody CreateListingRequest request) {
        return listingService.create(request);
    }

    @GetMapping("/{id}")
    public ListingResponse getById(@PathVariable long id) {
        return listingService.getById(id);
    }

    @GetMapping
    public List<ListingResponse> getAll() {
        return listingService.getAll();
    }

}
