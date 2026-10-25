package com.ozodbek.classifieds.listing;

import com.ozodbek.classifieds.category.Category;
import com.ozodbek.classifieds.category.CategoryRepository;
import com.ozodbek.classifieds.category.attribute.AttributeValidationService;
import com.ozodbek.classifieds.exception.CategoryNotFoundException;
import com.ozodbek.classifieds.exception.ListingNotFoundException;
import com.ozodbek.classifieds.listing.dto.CreateListingRequest;
import com.ozodbek.classifieds.listing.dto.ListingResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ListingService {

    private final CategoryRepository categoryRepository;
    private final ListingRepository listingRepository;
    private final AttributeValidationService validationService;

    public ListingService(CategoryRepository categoryRepository,
                          ListingRepository listingRepository,
                          AttributeValidationService validationService) {
        this.listingRepository = listingRepository;
        this.validationService = validationService;
        this.categoryRepository = categoryRepository;
    }

    public ListingResponse create(CreateListingRequest request) {

        Category category = categoryRepository.findById(request.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("Category not found."));

        validationService.validate(request.categoryId(), request.attributes());

        var listing = new Listing(request.title(), request.price(), category, request.attributes());

        listingRepository.save(listing);

        return ListingResponse.from(listing);
    }

    public ListingResponse getById(Long id) {
        var listing = findById(id);

        return ListingResponse.from(listing);
    }

    public List<ListingResponse> getAll() {
        return listingRepository.findAll()
                .stream()
                .map(ListingResponse::from)
                .toList();
    }

    private Listing findById(Long id) {
        return listingRepository.findById(id)
                .orElseThrow(() -> new ListingNotFoundException("Listing not found."));
    }
}
