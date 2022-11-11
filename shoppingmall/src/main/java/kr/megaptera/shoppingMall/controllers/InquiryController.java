package kr.megaptera.shoppingMall.controllers;

import kr.megaptera.shoppingMall.dtos.CreateInquiryDto;
import kr.megaptera.shoppingMall.dtos.InquiryContentBlankExceptionDto;
import kr.megaptera.shoppingMall.dtos.InquiryDto;
import kr.megaptera.shoppingMall.dtos.InquiryDtos;
import kr.megaptera.shoppingMall.exceptions.InquiryContentBlankException;
import kr.megaptera.shoppingMall.services.CreateInquiryService;
import kr.megaptera.shoppingMall.services.GetInquiryListService;
import kr.megaptera.shoppingMall.services.GetMyInquiryListService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inquiries")
public class InquiryController {
    private final GetInquiryListService getInquiryListService;
    private final GetMyInquiryListService getMyInquiryListService;
    private final CreateInquiryService createInquiryService;

    public InquiryController(
        GetInquiryListService getInquiryListService,
        GetMyInquiryListService getMyInquiryListService,
        CreateInquiryService createInquiryService) {
        this.getInquiryListService = getInquiryListService;
        this.getMyInquiryListService = getMyInquiryListService;
        this.createInquiryService = createInquiryService;
    }

    @GetMapping("/products/{id}")
    public InquiryDtos inquiryList(
        @PathVariable("id") Long productId,
        @RequestAttribute("userId") Long userId,
        @RequestParam(required = false, defaultValue = "1") Integer page) {

        return getInquiryListService.getInquiryList(productId, userId, page);
    }

    @GetMapping("/products/{id}/users")
    public InquiryDtos myInquiryList(
        @PathVariable("id") Long productId,
        @RequestAttribute("userId") Long userId,
        @RequestParam(required = false, defaultValue = "1") Integer page) {

        return getMyInquiryListService.getMyInquiryList(productId, userId, page);
    }

    @PostMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public InquiryDto createInquiry(
        @PathVariable("id") Long productId,
        @RequestAttribute("userId") Long userId,
        @RequestBody @Validated CreateInquiryDto createInquiryDto) {

        return createInquiryService.createInquiry(productId, userId, createInquiryDto);
    }

    @ExceptionHandler(InquiryContentBlankException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public InquiryContentBlankExceptionDto inquiryContentBlankException() {
        return new InquiryContentBlankExceptionDto();
    }
}
