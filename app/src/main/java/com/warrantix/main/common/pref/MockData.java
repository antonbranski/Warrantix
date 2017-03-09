package com.warrantix.main.common.pref;

import android.net.Uri;

import com.warrantix.main.R;
import com.warrantix.main.common.rest.model.Brand;
import com.warrantix.main.common.rest.model.Cart;
import com.warrantix.main.common.rest.model.Contact;
import com.warrantix.main.common.rest.model.Deal;
import com.warrantix.main.common.rest.model.Dealer;
import com.warrantix.main.common.rest.model.Message;
import com.warrantix.main.common.rest.model.MsrpCurrency;
import com.warrantix.main.common.rest.model.Order;
import com.warrantix.main.common.rest.model.PersonalDetail;
import com.warrantix.main.common.rest.model.Product;
import com.warrantix.main.common.rest.model.Rate;
import com.warrantix.main.common.rest.model.RelatedProduct;
import com.warrantix.main.common.rest.model.Review;
import com.warrantix.main.common.rest.model.RoleInfo;
import com.warrantix.main.common.rest.model.Service;
import com.warrantix.main.common.rest.model.ServiceCenter;
import com.warrantix.main.common.rest.model.ServiceCompany;
import com.warrantix.main.common.rest.model.UsedProduct;
import com.warrantix.main.common.rest.model.Warranty;
import com.warrantix.main.common.rest.request.AddProductToCartRequest;
import com.warrantix.main.common.rest.response.CustomerResponse;

import java.util.ArrayList;
import java.util.List;

public class MockData{

    public static final List<Product> mockCartProducts = new ArrayList<>();
    public static final List<Product> mockMyProducts = new ArrayList<>();
    public static final List<Product> mockProducts = new ArrayList<>();
    public static final List<Review> mockReviews = new ArrayList<>();
    public static final List<Rate> mockRates = new ArrayList<>();
    public static final List<RelatedProduct> mockRelateProducts = new ArrayList<>();
    public static final List<ServiceCompany> mockServiceCompanies = new ArrayList<>();
    public static final List<Message> mockRevealMessages = new ArrayList<>();
    public static final List<Message> mockReferMessages = new ArrayList<>();
    public static final List<Message> mockChatMessages = new ArrayList<>();
    public static final List<Message> mockReminderMessages = new ArrayList<>();
    public static final List<Dealer> mockDealers = new ArrayList<>();
    public static final List<Order> mockOrders = new ArrayList<>();
    public static final List<UsedProduct> mockUsedProducts = new ArrayList<>();
    public static final List<Service> mockServices = new ArrayList<>();
    public static final List<Cart> mockCarts = new ArrayList<>();
    public static final List<Contact> mockContacts = new ArrayList<>();
    public static final List<CustomerResponse> mockCustomers = new ArrayList<>();
    public static final List<String> mockBrandImgUrls = new ArrayList<>();
    public static final List<Warranty> mockWarranty = new ArrayList<>();
    public static final List<PersonalDetail> mockPersonalDetail = new ArrayList<>();

    public static List<Product> createCartProducts() {
        if (mockCartProducts.size() == 0) {
            Product product0 = new Product();
            product0.setId("575a963055b8439f41aac0ff");
            product0.setName("Karizma ZMR");
            product0.setCreatedAt("2016-06-10");
            product0.setUpdatedAt("2016-06-10");
            product0.setBrandID("heroID");
            product0.setClass_("150cc");
            product0.setCategory("appliances");
            product0.setModel("Hero MotoCorp");
            product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
            product0.setSerialNumberStart("string");
            product0.setSerialNumberEnd("string");
            product0.setLoading("string");
            product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
            product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
            product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product0.setMpn("string");
            product0.setProductionDate("2016-06-10");
            product0.setReleaseDate("2016-06-10");
            product0.setSku("690");
            product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
            product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
            product0.setMsrp(828);
            MsrpCurrency msrpCurrency0 = new MsrpCurrency();
            msrpCurrency0.setMsrpCurrency("INR");
            product0.setMsrpCurrency(msrpCurrency0);
            product0.setMsrpCurrencySymb("&#8377;");
            product0.setFeatured(false);
            product0.setPopular(true);
            createReviews();
            List<Review> reviews0 = new ArrayList<>();
            reviews0.add(mockReviews.get(0));
            reviews0.add(mockReviews.get(1));
            reviews0.add(mockReviews.get(2));
            reviews0.add(mockReviews.get(3));
            product0.setReviews(reviews0);
            createRates();
            List<Rate> rates0 = new ArrayList<>();
            rates0.add(mockRates.get(0));
            rates0.add(mockRates.get(1));
            rates0.add(mockRates.get(2));
            rates0.add(mockRates.get(3));
            product0.setRate(rates0);
            List<String> mechanicalFixes0 = new ArrayList<>();
            mechanicalFixes0.add("mechanicalFix1");
            mechanicalFixes0.add("mechanicalFix2");
            mechanicalFixes0.add("mechanicalFix3");
            product0.setMechanicalFixes(mechanicalFixes0);
            List<String> engineFixes0 = new ArrayList<>();
            engineFixes0.add("engineFix1");
            engineFixes0.add("engineFix2");
            engineFixes0.add("engineFix3");
            product0.setEngineFixes(engineFixes0);
            List<String> electronicalFixes0 = new ArrayList<>();
            electronicalFixes0.add("electronicalFix1");
            electronicalFixes0.add("electronicalFix2");
            electronicalFixes0.add("electronicalFix3");
            product0.setElectricalFixes(electronicalFixes0);
            List<String> imageUrls0 = new ArrayList<>();
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product0.setImageUrls(imageUrls0);

            mockCartProducts.add(product0);

            Product product1 = new Product();
            product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            product1.setClass_(product0.getClass_());
            product1.setLoading(product0.getLoading());
            product1.setManualImageThmb(product0.getManualImageThmb());
            product1.setManualUrl(product0.getManualUrl());
            product1.setMpn(product0.getMpn());
            product1.setMsrpCurrency(product0.getMsrpCurrency());
            product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product1.setPopular(product0.getPopular());
            product1.setProductionDate(product0.getProductionDate());
            product1.setRate(product0.getRate());
            product1.setReleaseDate(product0.getReleaseDate());
            product1.setReviews(product0.getReviews());
            product1.setSerialNumberEnd(product0.getSerialNumberEnd());
            product1.setSerialNumberStart(product0.getSerialNumberStart());
            product1.setUpdatedAt(product0.getUpdatedAt());
            product1.setId("575a96305764b195b7c0f784");
            product1.setName("WT 650 CF Washing Machine");
            product1.setBrandID("GodrejID");
            product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
            product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
            product1.setCategory("appliances");
            product1.setSku("279");
            product1.setModel("WT 650 CF, Godrej");
            product1.setPopular(false);
            product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
            product1.setMsrp(940);
            createReviews();
            List<Review> reviews1 = new ArrayList<>();
            reviews1.add(mockReviews.get(2));
            reviews1.add(mockReviews.get(3));
            product1.setReviews(reviews1);
            createRates();
            List<Rate> rates1 = new ArrayList<>();
            rates1.add(mockRates.get(2));
            rates1.add(mockRates.get(3));
            product1.setRate(rates1);
            List<String> mechanicalFixes1 = new ArrayList<>();
            mechanicalFixes1.add("mechanicalFix1");
            mechanicalFixes1.add("mechanicalFix2");
            mechanicalFixes1.add("mechanicalFix3");
            product1.setMechanicalFixes(mechanicalFixes1);
            List<String> engineFixes1 = new ArrayList<>();
            engineFixes1.add("engineFix1");
            engineFixes1.add("engineFix2");
            engineFixes1.add("engineFix3");
            product1.setEngineFixes(engineFixes1);
            List<String> electronicalFixes1 = new ArrayList<>();
            electronicalFixes1.add("electronicalFix1");
            electronicalFixes1.add("electronicalFix2");
            electronicalFixes1.add("electronicalFix3");
            product1.setElectricalFixes(electronicalFixes1);
            List<String> imageUrls1 = new ArrayList<>();
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product1.setImageUrls(imageUrls1);
            mockCartProducts.add(product1);

            Product product2 = new Product();
            product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product2.setClass_(product0.getClass_());
            product2.setLoading(product0.getLoading());
            product2.setManualImageThmb(product0.getManualImageThmb());
            product2.setManualUrl(product0.getManualUrl());
            product2.setMpn(product0.getMpn());
            product2.setMsrpCurrency(product0.getMsrpCurrency());
            product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product2.setPopular(product0.getPopular());
            product2.setProductionDate(product0.getProductionDate());
            product2.setRate(product0.getRate());
            product2.setReleaseDate(product0.getReleaseDate());
            product2.setReviews(product0.getReviews());
            product2.setSerialNumberEnd(product0.getSerialNumberEnd());
            product2.setSerialNumberStart(product0.getSerialNumberStart());
            product2.setUpdatedAt(product0.getUpdatedAt());
            product2.setId("575a9630d5725ffc91c74eab");
            product2.setName("Karizma ZMR");
            product2.setBrandID("heroID");
            product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
            product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
            product2.setCategory("electronics");
            product2.setSku("471");
            product2.setModel("Hero MotoCorp");
            product2.setPopular(true);
            product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
            product2.setMsrp(718);
            createReviews();
            List<Review> reviews2 = new ArrayList<>();
            reviews2.add(mockReviews.get(4));
            reviews2.add(mockReviews.get(5));
            product2.setReviews(reviews2);
            createRates();
            List<Rate> rates2 = new ArrayList<>();
            rates2.add(mockRates.get(4));
            rates2.add(mockRates.get(5));
            List<String> mechanicalFixes2 = new ArrayList<>();
            mechanicalFixes2.add("mechanicalFix1");
            mechanicalFixes2.add("mechanicalFix2");
            mechanicalFixes2.add("mechanicalFix3");
            product2.setMechanicalFixes(mechanicalFixes2);
            List<String> engineFixes2 = new ArrayList<>();
            engineFixes2.add("engineFix1");
            engineFixes2.add("engineFix2");
            engineFixes2.add("engineFix3");
            product2.setEngineFixes(engineFixes2);
            List<String> electronicalFixes2 = new ArrayList<>();
            electronicalFixes2.add("electronicalFix1");
            electronicalFixes2.add("electronicalFix2");
            electronicalFixes2.add("electronicalFix3");
            product2.setElectricalFixes(electronicalFixes2);
            product2.setRate(rates2);
            List<String> imageUrls2 = new ArrayList<>();
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product2.setImageUrls(imageUrls2);

            mockCartProducts.add(product2);

            Product product3 = new Product();
            product3.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            product3.setClass_(product0.getClass_());
            product3.setLoading(product0.getLoading());
            product3.setManualImageThmb(product0.getManualImageThmb());
            product3.setManualUrl(product0.getManualUrl());
            product3.setMpn(product0.getMpn());
            product3.setMsrpCurrency(product0.getMsrpCurrency());
            product3.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product3.setPopular(product0.getPopular());
            product3.setProductionDate(product0.getProductionDate());
            product3.setRate(product0.getRate());
            product3.setReleaseDate(product0.getReleaseDate());
            product3.setReviews(product0.getReviews());
            product3.setSerialNumberEnd(product0.getSerialNumberEnd());
            product3.setSerialNumberStart(product0.getSerialNumberStart());
            product3.setUpdatedAt(product0.getUpdatedAt());
            product3.setId("575a9630ac9474317edd8aab");
            product3.setName("WT 650 CF Washing Machine");
            product3.setBrandID("GodrejID");
            product3.setDescription("Officia nostrud aute eu irure magna deserunt occaecat mollit non nostrud sint irure. Velit occaecat Lorem proident officia do nisi pariatur eiusmod proident veniam");
            product3.setShortDescription("Ut enim magna nostrud ullamco.");
            product3.setCategory("electronics");
            product3.setModel("WT 650 CF, Godrej");
            product3.setPopular(true);
            product3.setSku("499");
            product3.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
            product3.setMsrp(354);
            createReviews();
            List<Review> reviews3 = new ArrayList<>();
            reviews3.add(mockReviews.get(6));
            reviews3.add(mockReviews.get(7));
            product3.setReviews(reviews3);
            createRates();
            List<Rate> rates3 = new ArrayList<>();
            rates3.add(mockRates.get(6));
            rates3.add(mockRates.get(7));
            product3.setMechanicalFixes(mechanicalFixes0);
            product3.setEngineFixes(engineFixes0);
            product3.setElectricalFixes(electronicalFixes0);
            product3.setRate(rates3);
            List<String> imageUrls3 = new ArrayList<>();
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product3.setImageUrls(imageUrls3);

            mockCartProducts.add(product3);
        }
        return mockCartProducts;
    }

    public static List<Product> createMyProducts() {
        if (mockMyProducts.size() == 0) {
            Product product0 = new Product();
            product0.setId("575a963055b8439f41aac0ff");
            product0.setName("Karizma ZMR");
            product0.setCreatedAt("2016-06-10");
            product0.setUpdatedAt("2016-06-10");
            product0.setBrandID("heroID");
            product0.setClass_("150cc");
            product0.setCategory("appliances");
            product0.setModel("Hero MotoCorp");
            product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
            product0.setSerialNumberStart("string");
            product0.setSerialNumberEnd("string");
            product0.setLoading("string");
            product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
            product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
            product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product0.setMpn("string");
            product0.setProductionDate("2016-06-10");
            product0.setReleaseDate("2016-06-10");
            product0.setSku("690");
            product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
            product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
            product0.setMsrp(828);
            MsrpCurrency msrpCurrency0 = new MsrpCurrency();
            msrpCurrency0.setMsrpCurrency("INR");
            product0.setMsrpCurrency(msrpCurrency0);
            product0.setMsrpCurrencySymb("&#8377;");
            product0.setFeatured(false);
            product0.setPopular(true);
            createReviews();
            List<Review> reviews0 = new ArrayList<>();
            reviews0.add(mockReviews.get(0));
            reviews0.add(mockReviews.get(1));
            reviews0.add(mockReviews.get(2));
            reviews0.add(mockReviews.get(3));
            product0.setReviews(reviews0);
            createRates();
            List<Rate> rates0 = new ArrayList<>();
            rates0.add(mockRates.get(0));
            rates0.add(mockRates.get(1));
            rates0.add(mockRates.get(2));
            rates0.add(mockRates.get(3));
            product0.setRate(rates0);
            List<String> mechanicalFixes0 = new ArrayList<>();
                mechanicalFixes0.add("mechanicalFix1");
                mechanicalFixes0.add("mechanicalFix2");
                mechanicalFixes0.add("mechanicalFix3");
            product0.setMechanicalFixes(mechanicalFixes0);
            List<String> engineFixes0 = new ArrayList<>();
                engineFixes0.add("engineFix1");
                engineFixes0.add("engineFix2");
                engineFixes0.add("engineFix3");
            product0.setEngineFixes(engineFixes0);
            List<String> electronicalFixes0 = new ArrayList<>();
                electronicalFixes0.add("electronicalFix1");
                electronicalFixes0.add("electronicalFix2");
                electronicalFixes0.add("electronicalFix3");
            product0.setElectricalFixes(electronicalFixes0);
            List<String> imageUrls0 = new ArrayList<>();
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product0.setImageUrls(imageUrls0);

            mockMyProducts.add(product0);

            Product product1 = new Product();
            product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            product1.setClass_(product0.getClass_());
            product1.setLoading(product0.getLoading());
            product1.setManualImageThmb(product0.getManualImageThmb());
            product1.setManualUrl(product0.getManualUrl());
            product1.setMpn(product0.getMpn());
            product1.setMsrpCurrency(product0.getMsrpCurrency());
            product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product1.setPopular(product0.getPopular());
            product1.setProductionDate(product0.getProductionDate());
            product1.setRate(product0.getRate());
            product1.setReleaseDate(product0.getReleaseDate());
            product1.setReviews(product0.getReviews());
            product1.setSerialNumberEnd(product0.getSerialNumberEnd());
            product1.setSerialNumberStart(product0.getSerialNumberStart());
            product1.setUpdatedAt(product0.getUpdatedAt());
            product1.setId("575a96305764b195b7c0f784");
            product1.setName("WT 650 CF Washing Machine");
            product1.setBrandID("GodrejID");
            product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
            product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
            product1.setCategory("appliances");
            product1.setSku("279");
            product1.setModel("WT 650 CF, Godrej");
            product1.setPopular(false);
            product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
            product1.setMsrp(940);
            createReviews();
            List<Review> reviews1 = new ArrayList<>();
            reviews1.add(mockReviews.get(2));
            reviews1.add(mockReviews.get(3));
            product1.setReviews(reviews1);
            createRates();
            List<Rate> rates1 = new ArrayList<>();
            rates1.add(mockRates.get(2));
            rates1.add(mockRates.get(3));
            product1.setRate(rates1);
            List<String> mechanicalFixes1 = new ArrayList<>();
            mechanicalFixes1.add("mechanicalFix1");
            mechanicalFixes1.add("mechanicalFix2");
            mechanicalFixes1.add("mechanicalFix3");
            product1.setMechanicalFixes(mechanicalFixes1);
            List<String> engineFixes1 = new ArrayList<>();
            engineFixes1.add("engineFix1");
            engineFixes1.add("engineFix2");
            engineFixes1.add("engineFix3");
            product1.setEngineFixes(engineFixes1);
            List<String> electronicalFixes1 = new ArrayList<>();
            electronicalFixes1.add("electronicalFix1");
            electronicalFixes1.add("electronicalFix2");
            electronicalFixes1.add("electronicalFix3");
            product1.setElectricalFixes(electronicalFixes1);
            List<String> imageUrls1 = new ArrayList<>();
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product1.setImageUrls(imageUrls1);
            mockMyProducts.add(product1);

            Product product2 = new Product();
            product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product2.setClass_(product0.getClass_());
            product2.setLoading(product0.getLoading());
            product2.setManualImageThmb(product0.getManualImageThmb());
            product2.setManualUrl(product0.getManualUrl());
            product2.setMpn(product0.getMpn());
            product2.setMsrpCurrency(product0.getMsrpCurrency());
            product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product2.setPopular(product0.getPopular());
            product2.setProductionDate(product0.getProductionDate());
            product2.setRate(product0.getRate());
            product2.setReleaseDate(product0.getReleaseDate());
            product2.setReviews(product0.getReviews());
            product2.setSerialNumberEnd(product0.getSerialNumberEnd());
            product2.setSerialNumberStart(product0.getSerialNumberStart());
            product2.setUpdatedAt(product0.getUpdatedAt());
            product2.setId("575a9630d5725ffc91c74eab");
            product2.setName("Karizma ZMR");
            product2.setBrandID("heroID");
            product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
            product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
            product2.setCategory("electronics");
            product2.setSku("471");
            product2.setModel("Hero MotoCorp");
            product2.setPopular(true);
            product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
            product2.setMsrp(718);
            createReviews();
            List<Review> reviews2 = new ArrayList<>();
            reviews2.add(mockReviews.get(4));
            reviews2.add(mockReviews.get(5));
            product2.setReviews(reviews2);
            createRates();
            List<Rate> rates2 = new ArrayList<>();
            rates2.add(mockRates.get(4));
            rates2.add(mockRates.get(5));
            List<String> mechanicalFixes2 = new ArrayList<>();
            mechanicalFixes2.add("mechanicalFix1");
            mechanicalFixes2.add("mechanicalFix2");
            mechanicalFixes2.add("mechanicalFix3");
            product2.setMechanicalFixes(mechanicalFixes2);
            List<String> engineFixes2 = new ArrayList<>();
            engineFixes2.add("engineFix1");
            engineFixes2.add("engineFix2");
            engineFixes2.add("engineFix3");
            product2.setEngineFixes(engineFixes2);
            List<String> electronicalFixes2 = new ArrayList<>();
            electronicalFixes2.add("electronicalFix1");
            electronicalFixes2.add("electronicalFix2");
            electronicalFixes2.add("electronicalFix3");
            product2.setElectricalFixes(electronicalFixes2);
            product2.setRate(rates2);
            List<String> imageUrls2 = new ArrayList<>();
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product2.setImageUrls(imageUrls2);

            mockMyProducts.add(product2);

            Product product3 = new Product();
            product3.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            product3.setClass_(product0.getClass_());
            product3.setLoading(product0.getLoading());
            product3.setManualImageThmb(product0.getManualImageThmb());
            product3.setManualUrl(product0.getManualUrl());
            product3.setMpn(product0.getMpn());
            product3.setMsrpCurrency(product0.getMsrpCurrency());
            product3.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product3.setPopular(product0.getPopular());
            product3.setProductionDate(product0.getProductionDate());
            product3.setRate(product0.getRate());
            product3.setReleaseDate(product0.getReleaseDate());
            product3.setReviews(product0.getReviews());
            product3.setSerialNumberEnd(product0.getSerialNumberEnd());
            product3.setSerialNumberStart(product0.getSerialNumberStart());
            product3.setUpdatedAt(product0.getUpdatedAt());
            product3.setId("575a9630ac9474317edd8aab");
            product3.setName("WT 650 CF Washing Machine");
            product3.setBrandID("GodrejID");
            product3.setDescription("Officia nostrud aute eu irure magna deserunt occaecat mollit non nostrud sint irure. Velit occaecat Lorem proident officia do nisi pariatur eiusmod proident veniam");
            product3.setShortDescription("Ut enim magna nostrud ullamco.");
            product3.setCategory("electronics");
            product3.setModel("WT 650 CF, Godrej");
            product3.setPopular(true);
            product3.setSku("499");
            product3.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
            product3.setMsrp(354);
            createReviews();
            List<Review> reviews3 = new ArrayList<>();
            reviews3.add(mockReviews.get(6));
            reviews3.add(mockReviews.get(7));
            product3.setReviews(reviews3);
            createRates();
            List<Rate> rates3 = new ArrayList<>();
            rates3.add(mockRates.get(6));
            rates3.add(mockRates.get(7));
            product3.setMechanicalFixes(mechanicalFixes0);
            product3.setEngineFixes(engineFixes0);
            product3.setElectricalFixes(electronicalFixes0);
            product3.setRate(rates3);
            List<String> imageUrls3 = new ArrayList<>();
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product3.setImageUrls(imageUrls3);

            mockMyProducts.add(product3);

            Product product4 = new Product();
            product4.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            product4.setClass_(product0.getClass_());
            product4.setLoading(product0.getLoading());
            product4.setManualImageThmb(product0.getManualImageThmb());
            product4.setManualUrl(product0.getManualUrl());
            product4.setMpn(product0.getMpn());
            product4.setMsrpCurrency(product0.getMsrpCurrency());
            product4.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product4.setPopular(product0.getPopular());
            product4.setProductionDate(product0.getProductionDate());
            product4.setRate(product0.getRate());
            product4.setReleaseDate(product0.getReleaseDate());
            product4.setReviews(product0.getReviews());
            product4.setSerialNumberEnd(product0.getSerialNumberEnd());
            product4.setSerialNumberStart(product0.getSerialNumberStart());
            product4.setUpdatedAt(product0.getUpdatedAt());
            product4.setId("575a963098d67ff6c6e38cc9");
            product4.setName("Impulse");
            product4.setBrandID("GodrejID");
            product4.setDescription("Eiusmod proident qui est cillum adipisicing minim culpa sunt minim duis dolor ea. Laboris elit elit consectetur tempor non pariatur nisi fugiat elit cupidatat in ea.");
            product4.setShortDescription("Enim ex est dolore commodo dolor occaecat.");
            product4.setCategory("vehicles");
            product4.setSku("323");
            product4.setModel("Hero MotoCorp");
            product4.setPopular(false);
            product4.setFeatureAndDetails("Culpa et sit ipsum et nostrud consectetur ea velit sunt. Culpa voluptate occaecat duis mollit id occaecat culpa eu duis ex cillum incididunt ullamco consectetur.");
            product4.setMsrp(147);
            createReviews();
            List<Review> reviews4 = new ArrayList<>();
            reviews4.add(mockReviews.get(8));
            reviews4.add(mockReviews.get(9));
            product4.setReviews(reviews4);
            createRates();
            List<Rate> rates4 = new ArrayList<>();
            rates4.add(mockRates.get(8));
            rates4.add(mockRates.get(9));
            product4.setMechanicalFixes(mechanicalFixes0);
            product4.setEngineFixes(engineFixes0);
            product4.setElectricalFixes(electronicalFixes0);
            product4.setRate(rates4);
            List<String> imageUrls4 = new ArrayList<>();
            imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product4.setImageUrls(imageUrls4);

            mockMyProducts.add(product4);

            Product product5 = new Product();
            product5.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product5.setClass_(product0.getClass_());
            product5.setLoading(product0.getLoading());
            product5.setManualImageThmb(product0.getManualImageThmb());
            product5.setManualUrl(product0.getManualUrl());
            product5.setMpn(product0.getMpn());
            product5.setMsrpCurrency(product0.getMsrpCurrency());
            product5.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product5.setPopular(product0.getPopular());
            product5.setProductionDate(product0.getProductionDate());
            product5.setRate(product0.getRate());
            product5.setReleaseDate(product0.getReleaseDate());
            product5.setReviews(product0.getReviews());
            product5.setSerialNumberEnd(product0.getSerialNumberEnd());
            product5.setSerialNumberStart(product0.getSerialNumberStart());
            product5.setUpdatedAt(product0.getUpdatedAt());
            product5.setId("575a96301d43624062a44c2c");
            product5.setName("Super Splendor");
            product5.setBrandID("heroID");
            product5.setDescription("Officia fugiat irure dolor in nulla esse culpa aliquip minim labore Lorem nostrud ea. Ut adipisicing exercitation est eu consectetur aliquip anim commodo deserunt sunt culpa aliquip sint.");
            product5.setShortDescription("Eu aute ut adipisicing consectetur reprehenderit voluptate pariatur.");
            product5.setCategory("vehicles");
            product5.setSku("418");
            product5.setModel("WT 650 CF, Godrej");
            product5.setPopular(false);
            product5.setFeatureAndDetails("Esse excepteur labore reprehenderit minim ullamco aliqua laboris sint sit est id. Fugiat dolore aliqua qui ut occaecat sint.");
            product5.setMsrp(531);
            createReviews();
            List<Review> reviews5 = new ArrayList<>();
            reviews5.add(mockReviews.get(10));
            reviews5.add(mockReviews.get(11));
            product5.setReviews(reviews5);
            createRates();
            List<Rate> rates5 = new ArrayList<>();
            rates5.add(mockRates.get(10));
            rates5.add(mockRates.get(11));
            product5.setMechanicalFixes(mechanicalFixes0);
            product5.setEngineFixes(engineFixes0);
            product5.setElectricalFixes(electronicalFixes0);
            product5.setRate(rates5);
            List<String> imageUrls5 = new ArrayList<>();
            imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product5.setImageUrls(imageUrls5);

            mockMyProducts.add(product5);

            Product product6 = new Product();
            product6.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            product6.setClass_(product0.getClass_());
            product6.setLoading(product0.getLoading());
            product6.setManualImageThmb(product0.getManualImageThmb());
            product6.setManualUrl(product0.getManualUrl());
            product6.setMpn(product0.getMpn());
            product6.setMsrpCurrency(product0.getMsrpCurrency());
            product6.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product6.setPopular(product0.getPopular());
            product6.setProductionDate(product0.getProductionDate());
            product6.setRate(product0.getRate());
            product6.setReleaseDate(product0.getReleaseDate());
            product6.setReviews(product0.getReviews());
            product6.setSerialNumberEnd(product0.getSerialNumberEnd());
            product6.setSerialNumberStart(product0.getSerialNumberStart());
            product6.setUpdatedAt(product0.getUpdatedAt());
            product6.setId("575a963012d141a611c9a358");
            product6.setName("Impulse");
            product6.setBrandID("GodrejID");
            product6.setDescription("Est laborum minim sit laboris cupidatat labore do. Dolor in veniam ut ad enim mollit esse non deserunt occaecat.");
            product6.setShortDescription("Est do ullamco ex eiusmod labore nisi nisi ipsum sunt est reprehenderit ea enim.");
            product6.setCategory("fitness");
            product6.setSku("722");
            product6.setModel("Hero MotoCorp");
            product6.setPopular(false);
            product6.setFeatureAndDetails("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
            product6.setMsrp(647);
            createReviews();
            List<Review> reviews6 = new ArrayList<>();
            reviews6.add(mockReviews.get(12));
            reviews6.add(mockReviews.get(13));
            product6.setReviews(reviews6);
            createRates();
            List<Rate> rates6 = new ArrayList<>();
            rates6.add(mockRates.get(12));
            rates6.add(mockRates.get(13));
            product6.setRate(rates6);
            product6.setMechanicalFixes(mechanicalFixes0);
            product6.setEngineFixes(engineFixes0);
            product6.setElectricalFixes(electronicalFixes0);
            List<String> imageUrls6 = new ArrayList<>();
            imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product6.setImageUrls(imageUrls6);

            mockMyProducts.add(product6);

            Product product7 = new Product();
            product7.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product7.setClass_(product0.getClass_());
            product7.setLoading(product0.getLoading());
            product7.setManualImageThmb(product0.getManualImageThmb());
            product7.setManualUrl(product0.getManualUrl());
            product7.setMpn(product0.getMpn());
            product7.setMsrpCurrency(product0.getMsrpCurrency());
            product7.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product7.setPopular(product0.getPopular());
            product7.setProductionDate(product0.getProductionDate());
            product7.setRate(product0.getRate());
            product7.setReleaseDate(product0.getReleaseDate());
            product7.setReviews(product0.getReviews());
            product7.setSerialNumberEnd(product0.getSerialNumberEnd());
            product7.setSerialNumberStart(product0.getSerialNumberStart());
            product7.setUpdatedAt(product0.getUpdatedAt());
            product7.setId("575a963030a86a88e818d703");
            product7.setName("Karizma ZMR");
            product7.setBrandID("heroID");
            product7.setDescription("Aliqua cillum aute deserunt Lorem mollit elit ipsum ut nulla sit enim tempor esse. Ea laborum proident ad irure tempor reprehenderit enim excepteur aliqua.");
            product7.setShortDescription("Adipisicing anim aliquip elit dolor sint.");
            product7.setCategory("fitness");
            product7.setSku("77");
            product7.setModel("WT 650 CF, Godrej");
            product7.setPopular(true);
            product7.setFeatureAndDetails("Mollit tempor voluptate tempor laboris ex aute tempor minim enim officia sint consectetur do. Proident Lorem nostrud incididunt duis dolor aliquip est amet.");
            product7.setMsrp(865);
            createReviews();
            List<Review> reviews7 = new ArrayList<>();
            reviews7.add(mockReviews.get(14));
            reviews7.add(mockReviews.get(15));
            product7.setReviews(reviews7);
            createRates();
            List<Rate> rates7 = new ArrayList<>();
            rates7.add(mockRates.get(14));
            rates7.add(mockRates.get(15));
            product7.setRate(rates7);
            product7.setMechanicalFixes(mechanicalFixes0);
            product7.setEngineFixes(engineFixes0);
            product7.setElectricalFixes(electronicalFixes0);
            List<String> imageUrls7 = new ArrayList<>();
            imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product7.setImageUrls(imageUrls7);

            mockMyProducts.add(product7);

            Product product8 = new Product();
            product8.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            product8.setClass_(product0.getClass_());
            product8.setLoading(product0.getLoading());
            product8.setManualImageThmb(product0.getManualImageThmb());
            product8.setManualUrl(product0.getManualUrl());
            product8.setMpn(product0.getMpn());
            product8.setMsrpCurrency(product0.getMsrpCurrency());
            product8.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
            product8.setPopular(product0.getPopular());
            product8.setProductionDate(product0.getProductionDate());
            product8.setRate(product0.getRate());
            product8.setReleaseDate(product0.getReleaseDate());
            product8.setReviews(product0.getReviews());
            product8.setSerialNumberEnd(product0.getSerialNumberEnd());
            product8.setSerialNumberStart(product0.getSerialNumberStart());
            product8.setUpdatedAt(product0.getUpdatedAt());
            product8.setId("575a96309655c54d320fe883");
            product8.setName("Karizma ZMR");
            product8.setBrandID("GodrejID");
            product8.setDescription("Laborum do id occaecat exercitation occaecat quis esse aute nostrud est aliqua. Labore ea cillum ut pariatur.");
            product8.setShortDescription("Incididunt cupidatat et consectetur commodo laboris est non.");
            product8.setCategory("personal");
            product8.setSku("701");
            product8.setModel("Hero MotoCorp");
            product8.setPopular(false);
            product8.setFeatureAndDetails("Exercitation adipisicing adipisicing aute sit Lorem tempor labore anim deserunt deserunt mollit officia et. Occaecat id sit ut cupidatat duis nostrud esse amet eu.");
            product8.setMsrp(117);
            createReviews();
            List<Review> reviews8 = new ArrayList<>();
            reviews8.add(mockReviews.get(16));
            reviews8.add(mockReviews.get(17));
            product8.setReviews(reviews8);
            createRates();
            List<Rate> rates8 = new ArrayList<>();
            rates8.add(mockRates.get(16));
            rates8.add(mockRates.get(17));
            product8.setRate(rates8);
            product8.setMechanicalFixes(mechanicalFixes0);
            product8.setEngineFixes(engineFixes0);
            product8.setElectricalFixes(electronicalFixes0);
            List<String> imageUrls8 = new ArrayList<>();
            imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product8.setImageUrls(imageUrls8);

            mockMyProducts.add(product8);

            Product product9 = new Product();
            product9.setId("575a963055b8439f41aac0ff");
            product9.setName("Karizma ZMR");
            product9.setCreatedAt("2016-06-10");
            product9.setUpdatedAt("2016-06-10");
            product9.setBrandID("heroID");
            product9.setClass_("150cc");
            product9.setCategory("appliances");
            product9.setModel("Hero MotoCorp");
            product9.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
            product9.setSerialNumberStart("string");
            product9.setSerialNumberEnd("string");
            product9.setLoading("string");
            product9.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
            product9.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
            product9.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            product9.setMpn("string");
            product9.setProductionDate("2016-06-10");
            product9.setReleaseDate("2016-06-10");
            product9.setSku("690");
            product9.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
            product9.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
            product9.setMsrp(828);
            MsrpCurrency msrpCurrency9 = new MsrpCurrency();
            msrpCurrency9.setMsrpCurrency("INR");
            product9.setMsrpCurrency(msrpCurrency9);
            product9.setMsrpCurrencySymb("&#8377;");
            product9.setFeatured(false);
            product9.setPopular(true);
            createReviews();
            List<Review> reviews9 = new ArrayList<>();
            reviews9.add(mockReviews.get(0));
            reviews9.add(mockReviews.get(1));
            reviews9.add(mockReviews.get(2));
            reviews9.add(mockReviews.get(3));
            product9.setReviews(reviews9);
            createRates();
            List<Rate> rates9 = new ArrayList<>();
            rates9.add(mockRates.get(0));
            rates9.add(mockRates.get(1));
            rates9.add(mockRates.get(2));
            rates9.add(mockRates.get(3));
            product9.setRate(rates9);
            List<String> mechanicalFixes9 = new ArrayList<>();
            mechanicalFixes9.add("mechanicalFix1");
            mechanicalFixes9.add("mechanicalFix2");
            mechanicalFixes9.add("mechanicalFix3");
            product9.setMechanicalFixes(mechanicalFixes9);
            List<String> engineFixes9 = new ArrayList<>();
            engineFixes9.add("engineFix1");
            engineFixes9.add("engineFix2");
            engineFixes9.add("engineFix3");
            product9.setEngineFixes(engineFixes9);
            List<String> electronicalFixes9 = new ArrayList<>();
            electronicalFixes9.add("electronicalFix1");
            electronicalFixes9.add("electronicalFix2");
            electronicalFixes9.add("electronicalFix3");
            product9.setElectricalFixes(electronicalFixes9);
            List<String> imageUrls9 = new ArrayList<>();
            imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
            product9.setImageUrls(imageUrls9);

            mockMyProducts.add(product9);
        }
        return mockMyProducts;
    }

    public static List<Product> createProducts (){

       if (mockProducts.size() == 0) {

           List<Review> reviews = new ArrayList<>();
           reviews = createReviews();

           List<Rate> rates = new ArrayList<>();
           rates = createRates();

           Product product0 = new Product();
           product0.setId("575a963055b8439f41aac0ff");
           product0.setName("Karizma ZMR");
           product0.setCreatedAt("2016-06-10");
           product0.setUpdatedAt("2016-06-10");
           product0.setBrandID("heroID");
           product0.setClass_("150cc");
           product0.setCategory("appliances");
           product0.setModel("Hero MotoCorp");
           product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
           product0.setSerialNumberStart("string");
           product0.setSerialNumberEnd("string");
           product0.setLoading("string");
           product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
           product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
           product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           product0.setMpn("string");
           product0.setProductionDate("2016-06-10");
           product0.setReleaseDate("2016-06-10");
           product0.setSku("690");
           product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
           product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
           product0.setMsrp(828);
           MsrpCurrency msrpCurrency0 = new MsrpCurrency();
           msrpCurrency0.setMsrpCurrency("INR");
           product0.setMsrpCurrency(msrpCurrency0);
           product0.setMsrpCurrencySymb("&#8377;");
           product0.setFeatured(false);
           product0.setPopular(true);
           List<Review> reviews0 = new ArrayList<>();
           reviews0.add(reviews.get(0));
           reviews0.add(reviews.get(1));
           reviews0.add(reviews.get(2));
           reviews0.add(reviews.get(3));
           product0.setReviews(reviews0);
           createRates();
           List<Rate> rates0 = new ArrayList<>();
           rates0.add(rates.get(0));
           rates0.add(rates.get(1));
           rates0.add(rates.get(2));
           rates0.add(rates.get(3));
           product0.setRate(rates0);
           List<String> imageUrls0 = new ArrayList<>();
           imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls0.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           product0.setImageUrls(imageUrls0);

           mockProducts.add(product0);

           Product product1 = new Product();
           product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product1.setClass_(product0.getClass_());
           product1.setLoading(product0.getLoading());
           product1.setManualImageThmb(product0.getManualImageThmb());
           product1.setManualUrl(product0.getManualUrl());
           product1.setMpn(product0.getMpn());
           product1.setMsrpCurrency(product0.getMsrpCurrency());
           product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product1.setPopular(product0.getPopular());
           product1.setProductionDate(product0.getProductionDate());
           product1.setRate(product0.getRate());
           product1.setReleaseDate(product0.getReleaseDate());
           product1.setReviews(product0.getReviews());
           product1.setSerialNumberEnd(product0.getSerialNumberEnd());
           product1.setSerialNumberStart(product0.getSerialNumberStart());
           product1.setUpdatedAt(product0.getUpdatedAt());
           product1.setId("575a96305764b195b7c0f784");
           product1.setName("WT 650 CF Washing Machine");
           product1.setBrandID("GodrejID");
           product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
           product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
           product1.setCategory("appliances");
           product1.setSku("279");
           product1.setModel("WT 650 CF, Godrej");
           product1.setPopular(false);
           product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
           product1.setMsrp(940);
           List<Review> reviews1 = new ArrayList<>();
           reviews1.add(reviews.get(2));
           reviews1.add(reviews.get(3));
           product1.setReviews(reviews1);
           List<Rate> rates1 = new ArrayList<>();
           rates1.add(rates.get(2));
           rates1.add(rates.get(3));
           product1.setRate(rates1);
           List<String> imageUrls1 = new ArrayList<>();
           imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls1.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product1.setImageUrls(imageUrls1);

           mockProducts.add(product1);

           Product product2 = new Product();
           product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           product2.setClass_(product0.getClass_());
           product2.setLoading(product0.getLoading());
           product2.setManualImageThmb(product0.getManualImageThmb());
           product2.setManualUrl(product0.getManualUrl());
           product2.setMpn(product0.getMpn());
           product2.setMsrpCurrency(product0.getMsrpCurrency());
           product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product2.setPopular(product0.getPopular());
           product2.setProductionDate(product0.getProductionDate());
           product2.setRate(product0.getRate());
           product2.setReleaseDate(product0.getReleaseDate());
           product2.setReviews(product0.getReviews());
           product2.setSerialNumberEnd(product0.getSerialNumberEnd());
           product2.setSerialNumberStart(product0.getSerialNumberStart());
           product2.setUpdatedAt(product0.getUpdatedAt());
           product2.setId("575a9630d5725ffc91c74eab");
           product2.setName("Karizma ZMR");
           product2.setBrandID("heroID");
           product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
           product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
           product2.setCategory("electronics");
           product2.setSku("471");
           product2.setModel("Hero MotoCorp");
           product2.setPopular(true);
           product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
           product2.setMsrp(718);
           List<Review> reviews2 = new ArrayList<>();
           reviews2.add(reviews.get(4));
           reviews2.add(reviews.get(5));
           product2.setReviews(reviews2);
           List<Rate> rates2 = new ArrayList<>();
           rates2.add(rates.get(4));
           rates2.add(rates.get(5));
           product2.setRate(rates2);
           List<String> imageUrls2 = new ArrayList<>();
           imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls2.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           product2.setImageUrls(imageUrls2);

           mockProducts.add(product2);

           Product product3 = new Product();
           product3.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product3.setClass_(product0.getClass_());
           product3.setLoading(product0.getLoading());
           product3.setManualImageThmb(product0.getManualImageThmb());
           product3.setManualUrl(product0.getManualUrl());
           product3.setMpn(product0.getMpn());
           product3.setMsrpCurrency(product0.getMsrpCurrency());
           product3.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product3.setPopular(product0.getPopular());
           product3.setProductionDate(product0.getProductionDate());
           product3.setRate(product0.getRate());
           product3.setReleaseDate(product0.getReleaseDate());
           product3.setReviews(product0.getReviews());
           product3.setSerialNumberEnd(product0.getSerialNumberEnd());
           product3.setSerialNumberStart(product0.getSerialNumberStart());
           product3.setUpdatedAt(product0.getUpdatedAt());
           product3.setId("575a9630ac9474317edd8aab");
           product3.setName("WT 650 CF Washing Machine");
           product3.setBrandID("heroID");
           product3.setDescription("Officia nostrud aute eu irure magna deserunt occaecat mollit non nostrud sint irure. Velit occaecat Lorem proident officia do nisi pariatur eiusmod proident veniam");
           product3.setShortDescription("Ut enim magna nostrud ullamco.");
           product3.setCategory("electronics");
           product3.setModel("WT 650 CF, Godrej");
           product3.setPopular(true);
           product3.setSku("499");
           product3.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
           product3.setMsrp(354);
           List<Review> reviews3 = new ArrayList<>();
           reviews3.add(reviews.get(6));
           reviews3.add(reviews.get(7));
           product3.setReviews(reviews3);
           List<Rate> rates3 = new ArrayList<>();
           rates3.add(rates.get(6));
           rates3.add(rates.get(7));
           product3.setRate(rates3);
           List<String> imageUrls3 = new ArrayList<>();
           imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls3.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product3.setImageUrls(imageUrls3);

           mockProducts.add(product3);

           Product product4 = new Product();
           product4.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product4.setClass_(product0.getClass_());
           product4.setLoading(product0.getLoading());
           product4.setManualImageThmb(product0.getManualImageThmb());
           product4.setManualUrl(product0.getManualUrl());
           product4.setMpn(product0.getMpn());
           product4.setMsrpCurrency(product0.getMsrpCurrency());
           product4.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product4.setPopular(product0.getPopular());
           product4.setProductionDate(product0.getProductionDate());
           product4.setRate(product0.getRate());
           product4.setReleaseDate(product0.getReleaseDate());
           product4.setReviews(product0.getReviews());
           product4.setSerialNumberEnd(product0.getSerialNumberEnd());
           product4.setSerialNumberStart(product0.getSerialNumberStart());
           product4.setUpdatedAt(product0.getUpdatedAt());
           product4.setId("575a963098d67ff6c6e38cc9");
           product4.setName("Impulse");
           product4.setBrandID("heroID");
           product4.setDescription("Eiusmod proident qui est cillum adipisicing minim culpa sunt minim duis dolor ea. Laboris elit elit consectetur tempor non pariatur nisi fugiat elit cupidatat in ea.");
           product4.setShortDescription("Enim ex est dolore commodo dolor occaecat.");
           product4.setCategory("vehicles");
           product4.setSku("323");
           product4.setModel("Hero MotoCorp");
           product4.setPopular(false);
           product4.setFeatureAndDetails("Culpa et sit ipsum et nostrud consectetur ea velit sunt. Culpa voluptate occaecat duis mollit id occaecat culpa eu duis ex cillum incididunt ullamco consectetur.");
           product4.setMsrp(147);
           List<Review> reviews4 = new ArrayList<>();
           reviews4.add(reviews.get(8));
           reviews4.add(reviews.get(9));
           product4.setReviews(reviews4);
           List<Rate> rates4 = new ArrayList<>();
           rates4.add(rates.get(8));
           rates4.add(rates.get(9));
           product4.setRate(rates4);
           List<String> imageUrls4 = new ArrayList<>();
           imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls4.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product4.setImageUrls(imageUrls4);

           mockProducts.add(product4);

           Product product5 = new Product();
           product5.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           product5.setClass_(product0.getClass_());
           product5.setLoading(product0.getLoading());
           product5.setManualImageThmb(product0.getManualImageThmb());
           product5.setManualUrl(product0.getManualUrl());
           product5.setMpn(product0.getMpn());
           product5.setMsrpCurrency(product0.getMsrpCurrency());
           product5.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product5.setPopular(product0.getPopular());
           product5.setProductionDate(product0.getProductionDate());
           product5.setRate(product0.getRate());
           product5.setReleaseDate(product0.getReleaseDate());
           product5.setReviews(product0.getReviews());
           product5.setSerialNumberEnd(product0.getSerialNumberEnd());
           product5.setSerialNumberStart(product0.getSerialNumberStart());
           product5.setUpdatedAt(product0.getUpdatedAt());
           product5.setId("575a96301d43624062a44c2c");
           product5.setName("Super Splendor");
           product5.setBrandID("heroID");
           product5.setDescription("Officia fugiat irure dolor in nulla esse culpa aliquip minim labore Lorem nostrud ea. Ut adipisicing exercitation est eu consectetur aliquip anim commodo deserunt sunt culpa aliquip sint.");
           product5.setShortDescription("Eu aute ut adipisicing consectetur reprehenderit voluptate pariatur.");
           product5.setCategory("vehicles");
           product5.setSku("418");
           product5.setModel("WT 650 CF, Godrej");
           product5.setPopular(false);
           product5.setFeatureAndDetails("Esse excepteur labore reprehenderit minim ullamco aliqua laboris sint sit est id. Fugiat dolore aliqua qui ut occaecat sint.");
           product5.setMsrp(531);
           List<Review> reviews5 = new ArrayList<>();
           reviews5.add(reviews.get(10));
           reviews5.add(reviews.get(11));
           product5.setReviews(reviews5);
           List<Rate> rates5 = new ArrayList<>();
           rates5.add(rates.get(10));
           rates5.add(rates.get(11));
           product5.setRate(rates5);
           List<String> imageUrls5 = new ArrayList<>();
           imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls5.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           product5.setImageUrls(imageUrls5);

           mockProducts.add(product5);

           Product product6 = new Product();
           product6.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product6.setClass_(product0.getClass_());
           product6.setLoading(product0.getLoading());
           product6.setManualImageThmb(product0.getManualImageThmb());
           product6.setManualUrl(product0.getManualUrl());
           product6.setMpn(product0.getMpn());
           product6.setMsrpCurrency(product0.getMsrpCurrency());
           product6.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product6.setPopular(product0.getPopular());
           product6.setProductionDate(product0.getProductionDate());
           product6.setRate(product0.getRate());
           product6.setReleaseDate(product0.getReleaseDate());
           product6.setReviews(product0.getReviews());
           product6.setSerialNumberEnd(product0.getSerialNumberEnd());
           product6.setSerialNumberStart(product0.getSerialNumberStart());
           product6.setUpdatedAt(product0.getUpdatedAt());
           product6.setId("575a963012d141a611c9a358");
           product6.setName("Impulse");
           product6.setBrandID("heroID");
           product6.setDescription("Est laborum minim sit laboris cupidatat labore do. Dolor in veniam ut ad enim mollit esse non deserunt occaecat.");
           product6.setShortDescription("Est do ullamco ex eiusmod labore nisi nisi ipsum sunt est reprehenderit ea enim.");
           product6.setCategory("fitness");
           product6.setSku("722");
           product6.setModel("Hero MotoCorp");
           product6.setPopular(false);
           product6.setFeatureAndDetails("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
           product6.setMsrp(647);
           List<Review> reviews6 = new ArrayList<>();
           reviews6.add(reviews.get(12));
           reviews6.add(reviews.get(13));
           product6.setReviews(reviews6);
           List<Rate> rates6 = new ArrayList<>();
           rates6.add(rates.get(12));
           rates6.add(rates.get(13));
           product6.setRate(rates6);
           List<String> imageUrls6 = new ArrayList<>();
           imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls6.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product6.setImageUrls(imageUrls6);

           mockProducts.add(product6);

           Product product7 = new Product();
           product7.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           product7.setClass_(product0.getClass_());
           product7.setLoading(product0.getLoading());
           product7.setManualImageThmb(product0.getManualImageThmb());
           product7.setManualUrl(product0.getManualUrl());
           product7.setMpn(product0.getMpn());
           product7.setMsrpCurrency(product0.getMsrpCurrency());
           product7.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product7.setPopular(product0.getPopular());
           product7.setProductionDate(product0.getProductionDate());
           product7.setRate(product0.getRate());
           product7.setReleaseDate(product0.getReleaseDate());
           product7.setReviews(product0.getReviews());
           product7.setSerialNumberEnd(product0.getSerialNumberEnd());
           product7.setSerialNumberStart(product0.getSerialNumberStart());
           product7.setUpdatedAt(product0.getUpdatedAt());
           product7.setId("575a963030a86a88e818d703");
           product7.setName("Karizma ZMR");
           product7.setBrandID("heroID");
           product7.setDescription("Aliqua cillum aute deserunt Lorem mollit elit ipsum ut nulla sit enim tempor esse. Ea laborum proident ad irure tempor reprehenderit enim excepteur aliqua.");
           product7.setShortDescription("Adipisicing anim aliquip elit dolor sint.");
           product7.setCategory("fitness");
           product7.setSku("77");
           product7.setModel("WT 650 CF, Godrej");
           product7.setPopular(true);
           product7.setFeatureAndDetails("Mollit tempor voluptate tempor laboris ex aute tempor minim enim officia sint consectetur do. Proident Lorem nostrud incididunt duis dolor aliquip est amet.");
           product7.setMsrp(865);
           List<Review> reviews7 = new ArrayList<>();
           reviews7.add(reviews.get(14));
           reviews7.add(reviews.get(15));
           product7.setReviews(reviews7);
           List<Rate> rates7 = new ArrayList<>();
           rates7.add(rates.get(14));
           rates7.add(rates.get(15));
           product7.setRate(rates7);
           List<String> imageUrls7 = new ArrayList<>();
           imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls7.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           product7.setImageUrls(imageUrls7);

           mockProducts.add(product7);

           Product product8 = new Product();
           product8.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product8.setClass_(product0.getClass_());
           product8.setLoading(product0.getLoading());
           product8.setManualImageThmb(product0.getManualImageThmb());
           product8.setManualUrl(product0.getManualUrl());
           product8.setMpn(product0.getMpn());
           product8.setMsrpCurrency(product0.getMsrpCurrency());
           product8.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product8.setPopular(product0.getPopular());
           product8.setProductionDate(product0.getProductionDate());
           product8.setRate(product0.getRate());
           product8.setReleaseDate(product0.getReleaseDate());
           product8.setReviews(product0.getReviews());
           product8.setSerialNumberEnd(product0.getSerialNumberEnd());
           product8.setSerialNumberStart(product0.getSerialNumberStart());
           product8.setUpdatedAt(product0.getUpdatedAt());
           product8.setId("575a96309655c54d320fe883");
           product8.setName("Karizma ZMR");
           product8.setBrandID("heroID");
           product8.setDescription("Laborum do id occaecat exercitation occaecat quis esse aute nostrud est aliqua. Labore ea cillum ut pariatur.");
           product8.setShortDescription("Incididunt cupidatat et consectetur commodo laboris est non.");
           product8.setCategory("personal");
           product8.setSku("701");
           product8.setModel("Hero MotoCorp");
           product8.setPopular(false);
           product8.setFeatureAndDetails("Exercitation adipisicing adipisicing aute sit Lorem tempor labore anim deserunt deserunt mollit officia et. Occaecat id sit ut cupidatat duis nostrud esse amet eu.");
           product8.setMsrp(117);
           List<Review> reviews8 = new ArrayList<>();
           reviews8.add(reviews.get(16));
           reviews8.add(reviews.get(17));
           product8.setReviews(reviews8);
           List<Rate> rates8 = new ArrayList<>();
           rates8.add(rates.get(16));
           rates8.add(rates.get(17));
           product8.setRate(rates8);
           List<String> imageUrls8 = new ArrayList<>();
           imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls8.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product8.setImageUrls(imageUrls8);

           mockProducts.add(product8);

           Product product9 = new Product();
           product9.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           product9.setClass_(product0.getClass_());
           product9.setLoading(product0.getLoading());
           product9.setManualImageThmb(product0.getManualImageThmb());
           product9.setManualUrl(product0.getManualUrl());
           product9.setMpn(product0.getMpn());
           product9.setMsrpCurrency(product0.getMsrpCurrency());
           product9.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product9.setPopular(product0.getPopular());
           product9.setProductionDate(product0.getProductionDate());
           product9.setRate(product0.getRate());
           product9.setReleaseDate(product0.getReleaseDate());
           product9.setReviews(product0.getReviews());
           product9.setSerialNumberEnd(product0.getSerialNumberEnd());
           product9.setSerialNumberEnd(product0.getSerialNumberEnd());
           product9.setSerialNumberStart(product0.getSerialNumberStart());
           product9.setUpdatedAt(product0.getUpdatedAt());
           product9.setId("575a9630588a0334c9e1278b");
           product9.setName("Super Splendor");
           product9.setBrandID("heroID");
           product9.setDescription("Sunt qui id nostrud voluptate eu aliquip fugiat reprehenderit reprehenderit. Cillum in occaecat non pariatur.");
           product9.setShortDescription("Consectetur quis fugiat amet occaecat sit Lorem aliqua aliquip eu magna proident non.");
           product9.setCategory("personal");
           product9.setSku("172");
           product9.setModel("WT 650 CF, Godrej");
           product9.setPopular(false);
           product9.setFeatureAndDetails("Incididunt ex qui sint nostrud laborum nulla magna incididunt dolor ex laborum voluptate exercitation reprehenderit. Non fugiat tempor nulla sit laboris laboris ea ex minim laborum.");
           product9.setMsrp(37);
           List<Review> reviews9 = new ArrayList<>();
           reviews9.add(reviews.get(18));
           reviews9.add(reviews.get(19));
           product9.setReviews(reviews9);
           List<Rate> rates9 = new ArrayList<>();
           rates9.add(rates.get(18));
           rates9.add(rates.get(19));
           product9.setRate(rates9);
           List<String> imageUrls9 = new ArrayList<>();
           imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls9.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           product9.setImageUrls(imageUrls9);

           mockProducts.add(product9);

           Product product10 = new Product();
           product10.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product10.setClass_(product0.getClass_());
           product10.setLoading(product0.getLoading());
           product10.setManualImageThmb(product0.getManualImageThmb());
           product10.setManualUrl(product0.getManualUrl());
           product10.setMpn(product0.getMpn());
           product10.setMsrpCurrency(product0.getMsrpCurrency());
           product10.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product10.setPopular(product0.getPopular());
           product10.setProductionDate(product0.getProductionDate());
           product10.setRate(product0.getRate());
           product10.setReleaseDate(product0.getReleaseDate());
           product10.setReviews(product0.getReviews());
           product10.setSerialNumberEnd(product0.getSerialNumberEnd());
           product10.setSerialNumberStart(product0.getSerialNumberStart());
           product10.setUpdatedAt(product0.getUpdatedAt());
           product10.setId("575a96309655c54d320fe884");
           product10.setName("Super Splendor");
           product10.setBrandID("heroID");
           product10.setDescription("Laborum do id occaecat exercitation occaecat quis esse aute nostrud est aliqua. Labore ea cillum ut pariatur.");
           product10.setShortDescription("Incididunt cupidatat et consectetur commodo laboris est non.");
           product10.setCategory("assorted");
           product10.setSku("701");
           product10.setModel("Hero MotoCorp");
           product10.setPopular(true);
           product10.setFeatureAndDetails("Exercitation adipisicing adipisicing aute sit Lorem tempor labore anim deserunt deserunt mollit officia et. Occaecat id sit ut cupidatat duis nostrud esse amet eu.");
           product10.setMsrp(117);
           product10.setReviews(reviews8);
           product10.setRate(rates8);
           List<String> imageUrls10 = new ArrayList<>();
           imageUrls10.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls10.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls10.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           imageUrls10.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
           product10.setImageUrls(imageUrls10);
           mockProducts.add(product10);

           Product product11 = new Product();
           product11.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           product11.setClass_(product0.getClass_());
           product11.setLoading(product0.getLoading());
           product11.setManualImageThmb(product0.getManualImageThmb());
           product11.setManualUrl(product0.getManualUrl());
           product11.setMpn(product0.getMpn());
           product11.setMsrpCurrency(product0.getMsrpCurrency());
           product11.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
           product11.setPopular(product0.getPopular());
           product11.setProductionDate(product0.getProductionDate());
           product11.setRate(product0.getRate());
           product11.setReleaseDate(product0.getReleaseDate());
           product11.setReviews(product0.getReviews());
           product11.setSerialNumberEnd(product0.getSerialNumberEnd());
           product11.setSerialNumberEnd(product0.getSerialNumberEnd());
           product11.setSerialNumberStart(product0.getSerialNumberStart());
           product11.setUpdatedAt(product0.getUpdatedAt());
           product11.setId("575a9630588a0334c9e1278c");
           product11.setName("Impulse");
           product11.setBrandID("heroID");
           product11.setDescription("Sunt qui id nostrud voluptate eu aliquip fugiat reprehenderit reprehenderit. Cillum in occaecat non pariatur.");
           product11.setShortDescription("Consectetur quis fugiat amet occaecat sit Lorem aliqua aliquip eu magna proident non.");
           product11.setCategory("assorted");
           product11.setSku("172");
           product11.setModel("WT 650 CF, Godrej");
           product11.setPopular(false);
           product11.setFeatureAndDetails("Incididunt ex qui sint nostrud laborum nulla magna incididunt dolor ex laborum voluptate exercitation reprehenderit. Non fugiat tempor nulla sit laboris laboris ea ex minim laborum.");
           product11.setMsrp(37);
           product11.setReviews(reviews9);
           product11.setRate(rates9);
           List<String> imageUrls11 = new ArrayList<>();
           imageUrls11.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls11.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls11.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls11.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           product11.setImageUrls(imageUrls11);

           mockProducts.add(product11);

           Product product12 = new Product();
           product12.setId("575a96305764b195b7c0f999");
           product12.setName("Hunk");
           product12.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.walletbrand_super_splendor).toString());
           product12.setBrandID("heroID");
           product12.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
           product12.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
           product12.setCategory("appliances");
           product12.setSku("279");
           product12.setPopular(true);
           product12.setModel("Hero Motocorp");
           product12.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
           product12.setMsrp(940);
           List<String> imageUrls12 = new ArrayList<>();
           imageUrls12.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls12.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls12.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls12.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           product12.setImageUrls(imageUrls12);

           mockProducts.add(product12);

           Product product13 = new Product();
           product13.setId("575a96305764b195b7c0f998");
           product13.setName("Hunk");
           product13.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.walletbrand_impulse_bike).toString());
           product13.setBrandID("heroID");
           product13.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
           product13.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
           product13.setCategory("appliances");
           product13.setSku("279");
           product13.setModel("Hero Motocorp");
           product13.setPopular(false);
           product13.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
           product13.setMsrp(940);
           List<String> imageUrls13 = new ArrayList<>();
           imageUrls13.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
           imageUrls13.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls13.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           imageUrls13.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.product_detail_zmr).toString());
           product13.setImageUrls(imageUrls13);

           mockProducts.add(product13);
       }

        return mockProducts;
    }

    public static List<Product> createProductsWithSubcategory (){

        List<Product> products = new ArrayList<>();

        List<Review> reviews = new ArrayList<>();
        reviews = createReviews();

        List<Rate> rates = new ArrayList<>();
        rates = createRates();

        Product product0 = new Product();
        product0.setId("575a963055b8439f41aac0ff");
        product0.setName("Karizma ZMR");
        product0.setCreatedAt("2016-06-10");
        product0.setUpdatedAt("2016-06-10");
        product0.setBrandID("warrantixID");
        product0.setClass_("150cc");
        product0.setCategory("Refrigerators");
        product0.setModel("Hero MotoCorp");
        product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
        product0.setSerialNumberStart("string");
        product0.setSerialNumberEnd("string");
        product0.setLoading("string");
        product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
        product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
        product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
        product0.setMpn("string");
        product0.setProductionDate("2016-06-10");
        product0.setReleaseDate("2016-06-10");
        product0.setSku("690");
        product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
        product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
        product0.setMsrp(828);
        MsrpCurrency msrpCurrency0 = new MsrpCurrency();
        msrpCurrency0.setMsrpCurrency("INR");
        product0.setMsrpCurrency(msrpCurrency0);
        product0.setMsrpCurrencySymb("&#8377;");
        product0.setFeatured(false);
        product0.setPopular(false);
        List<Review> reviews0 = new ArrayList<>();
        reviews0.add(reviews.get(0));
        reviews0.add(reviews.get(1));
        reviews0.add(reviews.get(2));
        reviews0.add(reviews.get(3));
        product0.setReviews(reviews0);
        List<Rate> rates0 = new ArrayList<>();
        rates0.add(rates.get(0));
        rates0.add(rates.get(1));
        rates0.add(rates.get(2));
        rates0.add(rates.get(3));
        product0.setRate(rates0);

        products.add(product0);

        Product product1 = new Product();
        product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
        product1.setClass_(product0.getClass_());
        product1.setLoading(product0.getLoading());
        product1.setManualImageThmb(product0.getManualImageThmb());
        product1.setManualUrl(product0.getManualUrl());
        product1.setMpn(product0.getMpn());
        product1.setMsrpCurrency(product0.getMsrpCurrency());
        product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product1.setPopular(product0.getPopular());
        product1.setProductionDate(product0.getProductionDate());
        product1.setRate(product0.getRate());
        product1.setReleaseDate(product0.getReleaseDate());
        product1.setReviews(product0.getReviews());
        product1.setSerialNumberEnd(product0.getSerialNumberEnd());
        product1.setSerialNumberStart(product0.getSerialNumberStart());
        product1.setUpdatedAt(product0.getUpdatedAt());
        product1.setId("575a96305764b195b7c0f784");
        product1.setName("WT 650 CF Washing Machine");
        product1.setBrandID("warrantixID");
        product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product1.setCategory("Washing Machine");
        product1.setSku("279");
        product1.setModel("WT 650 CF, Godrej");
        product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product1.setMsrp(940);
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(reviews.get(2));
        reviews1.add(reviews.get(3));
        product1.setReviews(reviews1);
        List<Rate> rates1 = new ArrayList<>();
        rates1.add(rates.get(2));
        rates1.add(rates.get(3));
        product1.setRate(rates1);

        products.add(product1);

        Product product2 = new Product();
        product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
        product2.setClass_(product0.getClass_());
        product2.setLoading(product0.getLoading());
        product2.setManualImageThmb(product0.getManualImageThmb());
        product2.setManualUrl(product0.getManualUrl());
        product2.setMpn(product0.getMpn());
        product2.setMsrpCurrency(product0.getMsrpCurrency());
        product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product2.setPopular(product0.getPopular());
        product2.setProductionDate(product0.getProductionDate());
        product2.setRate(product0.getRate());
        product2.setReleaseDate(product0.getReleaseDate());
        product2.setReviews(product0.getReviews());
        product2.setSerialNumberEnd(product0.getSerialNumberEnd());
        product2.setSerialNumberStart(product0.getSerialNumberStart());
        product2.setUpdatedAt(product0.getUpdatedAt());
        product2.setId("575a9630d5725ffc91c74eab");
        product2.setName("Karizma ZMR");
        product2.setBrandID("warrantixID");
        product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
        product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
        product2.setCategory("Air Conditioners");
        product2.setSku("471");
        product2.setModel("Hero MotoCorp");
        product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product2.setMsrp(718);
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(reviews.get(4));
        reviews2.add(reviews.get(5));
        product2.setReviews(reviews2);
        List<Rate> rates2 = new ArrayList<>();
        rates2.add(rates.get(4));
        rates2.add(rates.get(5));
        product2.setRate(rates2);

        products.add(product2);

        Product product3 = new Product();
        product3.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
        product3.setClass_(product0.getClass_());
        product3.setLoading(product0.getLoading());
        product3.setManualImageThmb(product0.getManualImageThmb());
        product3.setManualUrl(product0.getManualUrl());
        product3.setMpn(product0.getMpn());
        product3.setMsrpCurrency(product0.getMsrpCurrency());
        product3.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product3.setPopular(product0.getPopular());
        product3.setProductionDate(product0.getProductionDate());
        product3.setRate(product0.getRate());
        product3.setReleaseDate(product0.getReleaseDate());
        product3.setReviews(product0.getReviews());
        product3.setSerialNumberEnd(product0.getSerialNumberEnd());
        product3.setSerialNumberStart(product0.getSerialNumberStart());
        product3.setUpdatedAt(product0.getUpdatedAt());
        product3.setId("575a9630ac9474317edd8aab");
        product3.setName("WT 650 CF Washing Machine");
        product3.setBrandID("warrantixID");
        product3.setDescription("Officia nostrud aute eu irure magna deserunt occaecat mollit non nostrud sint irure. Velit occaecat Lorem proident officia do nisi pariatur eiusmod proident veniam");
        product3.setShortDescription("Ut enim magna nostrud ullamco.");
        product3.setCategory("Air Coolers");
        product3.setModel("WT 650 CF, Godrej");
        product3.setSku("499");
        product3.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product3.setMsrp(354);
        List<Review> reviews3 = new ArrayList<>();
        reviews3.add(reviews.get(6));
        reviews3.add(reviews.get(7));
        product3.setReviews(reviews3);
        List<Rate> rates3 = new ArrayList<>();
        rates3.add(rates.get(6));
        rates3.add(rates.get(7));
        product3.setRate(rates3);

        products.add(product3);

        Product product4 = new Product();
        product4.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
        product4.setClass_(product0.getClass_());
        product4.setLoading(product0.getLoading());
        product4.setManualImageThmb(product0.getManualImageThmb());
        product4.setManualUrl(product0.getManualUrl());
        product4.setMpn(product0.getMpn());
        product4.setMsrpCurrency(product0.getMsrpCurrency());
        product4.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product4.setPopular(product0.getPopular());
        product4.setProductionDate(product0.getProductionDate());
        product4.setRate(product0.getRate());
        product4.setReleaseDate(product0.getReleaseDate());
        product4.setReviews(product0.getReviews());
        product4.setSerialNumberEnd(product0.getSerialNumberEnd());
        product4.setSerialNumberStart(product0.getSerialNumberStart());
        product4.setUpdatedAt(product0.getUpdatedAt());
        product4.setId("575a963098d67ff6c6e38cc9");
        product4.setName("Impulse");
        product4.setBrandID("warrantixID");
        product4.setDescription("Eiusmod proident qui est cillum adipisicing minim culpa sunt minim duis dolor ea. Laboris elit elit consectetur tempor non pariatur nisi fugiat elit cupidatat in ea.");
        product4.setShortDescription("Enim ex est dolore commodo dolor occaecat.");
        product4.setCategory("Vacuum Cleaners");
        product4.setSku("323");
        product4.setModel("Hero MotoCorp");
        product4.setFeatureAndDetails("Culpa et sit ipsum et nostrud consectetur ea velit sunt. Culpa voluptate occaecat duis mollit id occaecat culpa eu duis ex cillum incididunt ullamco consectetur.");
        product4.setMsrp(147);
        List<Review> reviews4 = new ArrayList<>();
        reviews4.add(reviews.get(8));
        reviews4.add(reviews.get(9));
        product4.setReviews(reviews4);
        List<Rate> rates4 = new ArrayList<>();
        rates4.add(rates.get(8));
        rates4.add(rates.get(9));
        product4.setRate(rates4);

        products.add(product4);

        Product product5 = new Product();
        product5.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
        product5.setClass_(product0.getClass_());
        product5.setLoading(product0.getLoading());
        product5.setManualImageThmb(product0.getManualImageThmb());
        product5.setManualUrl(product0.getManualUrl());
        product5.setMpn(product0.getMpn());
        product5.setMsrpCurrency(product0.getMsrpCurrency());
        product5.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product5.setPopular(product0.getPopular());
        product5.setProductionDate(product0.getProductionDate());
        product5.setRate(product0.getRate());
        product5.setReleaseDate(product0.getReleaseDate());
        product5.setReviews(product0.getReviews());
        product5.setSerialNumberEnd(product0.getSerialNumberEnd());
        product5.setSerialNumberStart(product0.getSerialNumberStart());
        product5.setUpdatedAt(product0.getUpdatedAt());
        product5.setId("575a96301d43624062a44c2c");
        product5.setName("Super Splendor");
        product5.setBrandID("warrantixID");
        product5.setDescription("Officia fugiat irure dolor in nulla esse culpa aliquip minim labore Lorem nostrud ea. Ut adipisicing exercitation est eu consectetur aliquip anim commodo deserunt sunt culpa aliquip sint.");
        product5.setShortDescription("Eu aute ut adipisicing consectetur reprehenderit voluptate pariatur.");
        product5.setCategory("Water Purifiers");
        product5.setSku("418");
        product5.setModel("WT 650 CF, Godrej");
        product5.setFeatureAndDetails("Esse excepteur labore reprehenderit minim ullamco aliqua laboris sint sit est id. Fugiat dolore aliqua qui ut occaecat sint.");
        product5.setMsrp(531);
        List<Review> reviews5 = new ArrayList<>();
        reviews5.add(reviews.get(10));
        reviews5.add(reviews.get(11));
        product5.setReviews(reviews5);
        List<Rate> rates5 = new ArrayList<>();
        rates5.add(rates.get(10));
        rates5.add(rates.get(11));
        product5.setRate(rates5);

        products.add(product5);

        Product product6 = new Product();
        product6.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
        product6.setClass_(product0.getClass_());
        product6.setLoading(product0.getLoading());
        product6.setManualImageThmb(product0.getManualImageThmb());
        product6.setManualUrl(product0.getManualUrl());
        product6.setMpn(product0.getMpn());
        product6.setMsrpCurrency(product0.getMsrpCurrency());
        product6.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product6.setPopular(product0.getPopular());
        product6.setProductionDate(product0.getProductionDate());
        product6.setRate(product0.getRate());
        product6.setReleaseDate(product0.getReleaseDate());
        product6.setReviews(product0.getReviews());
        product6.setSerialNumberEnd(product0.getSerialNumberEnd());
        product6.setSerialNumberStart(product0.getSerialNumberStart());
        product6.setUpdatedAt(product0.getUpdatedAt());
        product6.setId("575a963012d141a611c9a358");
        product6.setName("Impulse");
        product6.setBrandID("warrantixID");
        product6.setDescription("Est laborum minim sit laboris cupidatat labore do. Dolor in veniam ut ad enim mollit esse non deserunt occaecat.");
        product6.setShortDescription("Est do ullamco ex eiusmod labore nisi nisi ipsum sunt est reprehenderit ea enim.");
        product6.setCategory("MicorWaves");
        product6.setSku("722");
        product6.setModel("Hero MotoCorp");
        product6.setFeatureAndDetails("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
        product6.setMsrp(647);
        List<Review> reviews6 = new ArrayList<>();
        reviews6.add(reviews.get(12));
        reviews6.add(reviews.get(13));
        product6.setReviews(reviews6);
        List<Rate> rates6 = new ArrayList<>();
        rates6.add(rates.get(12));
        rates6.add(rates.get(13));
        product6.setRate(rates6);

        products.add(product6);

        Product product7 = new Product();
        product7.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
        product7.setClass_(product0.getClass_());
        product7.setLoading(product0.getLoading());
        product7.setManualImageThmb(product0.getManualImageThmb());
        product7.setManualUrl(product0.getManualUrl());
        product7.setMpn(product0.getMpn());
        product7.setMsrpCurrency(product0.getMsrpCurrency());
        product7.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product7.setPopular(product0.getPopular());
        product7.setProductionDate(product0.getProductionDate());
        product7.setRate(product0.getRate());
        product7.setReleaseDate(product0.getReleaseDate());
        product7.setReviews(product0.getReviews());
        product7.setSerialNumberEnd(product0.getSerialNumberEnd());
        product7.setSerialNumberStart(product0.getSerialNumberStart());
        product7.setUpdatedAt(product0.getUpdatedAt());
        product7.setId("575a963030a86a88e818d703");
        product7.setName("Karizma ZMR");
        product7.setBrandID("warrantixID");
        product7.setDescription("Aliqua cillum aute deserunt Lorem mollit elit ipsum ut nulla sit enim tempor esse. Ea laborum proident ad irure tempor reprehenderit enim excepteur aliqua.");
        product7.setShortDescription("Adipisicing anim aliquip elit dolor sint.");
        product7.setCategory("Mobile Phones");
        product7.setSku("77");
        product7.setModel("WT 650 CF, Godrej");
        product7.setFeatureAndDetails("Mollit tempor voluptate tempor laboris ex aute tempor minim enim officia sint consectetur do. Proident Lorem nostrud incididunt duis dolor aliquip est amet.");
        product7.setMsrp(865);
        List<Review> reviews7 = new ArrayList<>();
        reviews7.add(reviews.get(14));
        reviews7.add(reviews.get(15));
        product7.setReviews(reviews7);
        List<Rate> rates7 = new ArrayList<>();
        rates7.add(rates.get(14));
        rates7.add(rates.get(15));
        product7.setRate(rates7);

        products.add(product7);

        Product product8 = new Product();
        product8.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
        product8.setClass_(product0.getClass_());
        product8.setLoading(product0.getLoading());
        product8.setManualImageThmb(product0.getManualImageThmb());
        product8.setManualUrl(product0.getManualUrl());
        product8.setMpn(product0.getMpn());
        product8.setMsrpCurrency(product0.getMsrpCurrency());
        product8.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product8.setPopular(product0.getPopular());
        product8.setProductionDate(product0.getProductionDate());
        product8.setRate(product0.getRate());
        product8.setReleaseDate(product0.getReleaseDate());
        product8.setReviews(product0.getReviews());
        product8.setSerialNumberEnd(product0.getSerialNumberEnd());
        product8.setSerialNumberStart(product0.getSerialNumberStart());
        product8.setUpdatedAt(product0.getUpdatedAt());
        product8.setId("575a96309655c54d320fe883");
        product8.setName("Karizma ZMR");
        product8.setBrandID("warrantixID");
        product8.setDescription("Laborum do id occaecat exercitation occaecat quis esse aute nostrud est aliqua. Labore ea cillum ut pariatur.");
        product8.setShortDescription("Incididunt cupidatat et consectetur commodo laboris est non.");
        product8.setCategory("Dish Washers");
        product8.setSku("701");
        product8.setModel("Hero MotoCorp");
        product8.setFeatureAndDetails("Exercitation adipisicing adipisicing aute sit Lorem tempor labore anim deserunt deserunt mollit officia et. Occaecat id sit ut cupidatat duis nostrud esse amet eu.");
        product8.setMsrp(117);
        List<Review> reviews8 = new ArrayList<>();
        reviews8.add(reviews.get(16));
        reviews8.add(reviews.get(17));
        product8.setReviews(reviews8);
        List<Rate> rates8 = new ArrayList<>();
        rates8.add(rates.get(16));
        rates8.add(rates.get(17));
        product8.setRate(rates8);

        products.add(product8);

        Product product9 = new Product();
        product9.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
        product9.setClass_(product0.getClass_());
        product9.setLoading(product0.getLoading());
        product9.setManualImageThmb(product0.getManualImageThmb());
        product9.setManualUrl(product0.getManualUrl());
        product9.setMpn(product0.getMpn());
        product9.setMsrpCurrency(product0.getMsrpCurrency());
        product9.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product9.setPopular(product0.getPopular());
        product9.setProductionDate(product0.getProductionDate());
        product9.setRate(product0.getRate());
        product9.setReleaseDate(product0.getReleaseDate());
        product9.setReviews(product0.getReviews());
        product9.setSerialNumberEnd(product0.getSerialNumberEnd());
        product9.setSerialNumberEnd(product0.getSerialNumberEnd());
        product9.setSerialNumberStart(product0.getSerialNumberStart());
        product9.setUpdatedAt(product0.getUpdatedAt());
        product9.setId("575a9630588a0334c9e1278b");
        product9.setName("Super Splendor");
        product9.setBrandID("warrantixID");
        product9.setDescription("Sunt qui id nostrud voluptate eu aliquip fugiat reprehenderit reprehenderit. Cillum in occaecat non pariatur.");
        product9.setShortDescription("Consectetur quis fugiat amet occaecat sit Lorem aliqua aliquip eu magna proident non.");
        product9.setCategory("Televisions");
        product9.setSku("172");
        product9.setModel("WT 650 CF, Godrej");
        product9.setFeatureAndDetails("Incididunt ex qui sint nostrud laborum nulla magna incididunt dolor ex laborum voluptate exercitation reprehenderit. Non fugiat tempor nulla sit laboris laboris ea ex minim laborum.");
        product9.setMsrp(37);
        List<Review> reviews9 = new ArrayList<>();
        reviews9.add(reviews.get(18));
        reviews9.add(reviews.get(19));
        product9.setReviews(reviews9);
        List<Rate> rates9 = new ArrayList<>();
        rates9.add(rates.get(18));
        rates9.add(rates.get(19));
        product9.setRate(rates9);

        products.add(product9);

        Product product10 = new Product();
        product10.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
        product10.setClass_(product0.getClass_());
        product10.setLoading(product0.getLoading());
        product10.setManualImageThmb(product0.getManualImageThmb());
        product10.setManualUrl(product0.getManualUrl());
        product10.setMpn(product0.getMpn());
        product10.setMsrpCurrency(product0.getMsrpCurrency());
        product10.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product10.setPopular(product0.getPopular());
        product10.setProductionDate(product0.getProductionDate());
        product10.setRate(product0.getRate());
        product10.setReleaseDate(product0.getReleaseDate());
        product10.setReviews(product0.getReviews());
        product10.setSerialNumberEnd(product0.getSerialNumberEnd());
        product10.setSerialNumberStart(product0.getSerialNumberStart());
        product10.setUpdatedAt(product0.getUpdatedAt());
        product10.setId("575a96309655c54d320fe884");
        product10.setName("Super Splendor");
        product10.setBrandID("warrantixID");
        product10.setDescription("Laborum do id occaecat exercitation occaecat quis esse aute nostrud est aliqua. Labore ea cillum ut pariatur.");
        product10.setShortDescription("Incididunt cupidatat et consectetur commodo laboris est non.");
        product10.setCategory("laptops");
        product10.setSku("701");
        product10.setModel("Hero MotoCorp");
        product10.setFeatureAndDetails("Exercitation adipisicing adipisicing aute sit Lorem tempor labore anim deserunt deserunt mollit officia et. Occaecat id sit ut cupidatat duis nostrud esse amet eu.");
        product10.setMsrp(117);
        product10.setReviews(reviews8);
        product10.setRate(rates8);

        products.add(product10);

        Product product11 = new Product();
        product11.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
        product11.setClass_(product0.getClass_());
        product11.setLoading(product0.getLoading());
        product11.setManualImageThmb(product0.getManualImageThmb());
        product11.setManualUrl(product0.getManualUrl());
        product11.setMpn(product0.getMpn());
        product11.setMsrpCurrency(product0.getMsrpCurrency());
        product11.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product11.setPopular(product0.getPopular());
        product11.setProductionDate(product0.getProductionDate());
        product11.setRate(product0.getRate());
        product11.setReleaseDate(product0.getReleaseDate());
        product11.setReviews(product0.getReviews());
        product11.setSerialNumberEnd(product0.getSerialNumberEnd());
        product11.setSerialNumberEnd(product0.getSerialNumberEnd());
        product11.setSerialNumberStart(product0.getSerialNumberStart());
        product11.setUpdatedAt(product0.getUpdatedAt());
        product11.setId("575a9630588a0334c9e1278c");
        product11.setName("Impulse");
        product11.setBrandID("warrantixID");
        product11.setDescription("Sunt qui id nostrud voluptate eu aliquip fugiat reprehenderit reprehenderit. Cillum in occaecat non pariatur.");
        product11.setShortDescription("Consectetur quis fugiat amet occaecat sit Lorem aliqua aliquip eu magna proident non.");
        product11.setCategory("Printers");
        product11.setSku("172");
        product11.setModel("WT 650 CF, Godrej");
        product11.setFeatureAndDetails("Incididunt ex qui sint nostrud laborum nulla magna incididunt dolor ex laborum voluptate exercitation reprehenderit. Non fugiat tempor nulla sit laboris laboris ea ex minim laborum.");
        product11.setMsrp(37);
        product11.setReviews(reviews9);
        product11.setRate(rates9);

        products.add(product11);

        Product product12 = new Product();
        product12.setId("575a96305764b195b7c0f999");
        product12.setName("Hunk");
        product12.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.walletbrand_super_splendor).toString());
        product12.setBrandID("warrantixID");
        product12.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product12.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product12.setCategory("Computers & Accessories");
        product12.setSku("279");
        product12.setModel("Hero Motocorp");
        product12.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product12.setMsrp(940);

        products.add(product12);

        Product product13 = new Product();
        product13.setId("575a96305764b195b7c0f998");
        product13.setName("Hunk");
        product13.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.walletbrand_impulse_bike).toString());
        product13.setBrandID("warrantixID");
        product13.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product13.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product13.setCategory("Treadmill");
        product13.setSku("279");
        product13.setModel("Hero Motocorp");
        product13.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product13.setMsrp(940);

        products.add(product13);

        return products;
    }

    public static List<Product> createProductsForWarrantixUsedProduct(){

        List<Product> products = new ArrayList<>();

        List<Review> reviews = new ArrayList<>();
        reviews = createReviews();

        List<Rate> rates = new ArrayList<>();
        rates = createRates();

        Product product0 = new Product();
        product0.setId("575a963055b8439f41aac0ff1");
        product0.setName("Lenovo Vibe K5");
        product0.setCreatedAt("2016-06-10");
        product0.setUpdatedAt("2016-06-10");
        product0.setBrandID("warrantixID");
        product0.setClass_("150cc");
        product0.setCategory("appliances");
        product0.setModel("Hero MotoCorp");
        product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
        product0.setSerialNumberStart("string");
        product0.setSerialNumberEnd("string");
        product0.setLoading("string");
        product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
        product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
        product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.marketplace_usedproduct_appliance1).toString());
        product0.setMpn("string");
        product0.setProductionDate("2016-06-10");
        product0.setReleaseDate("2016-06-10");
        product0.setSku("690");
        product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
        product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
        product0.setMsrp(828);
        MsrpCurrency msrpCurrency0 = new MsrpCurrency();
        msrpCurrency0.setMsrpCurrency("INR");
        product0.setMsrpCurrency(msrpCurrency0);
        product0.setMsrpCurrencySymb("&#8377;");
        product0.setFeatured(false);
        product0.setPopular(false);
        List<Review> reviews0 = new ArrayList<>();
        reviews0.add(reviews.get(0));
        reviews0.add(reviews.get(1));
        reviews0.add(reviews.get(2));
        reviews0.add(reviews.get(3));
        product0.setReviews(reviews0);
        List<Rate> rates0 = new ArrayList<>();
        rates0.add(rates.get(0));
        rates0.add(rates.get(1));
        rates0.add(rates.get(2));
        rates0.add(rates.get(3));
        product0.setRate(rates0);

        products.add(product0);

        Product product1 = new Product();
        product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.marketplace_usedproduct_appliance2).toString());
        product1.setClass_(product0.getClass_());
        product1.setLoading(product0.getLoading());
        product1.setManualImageThmb(product0.getManualImageThmb());
        product1.setManualUrl(product0.getManualUrl());
        product1.setMpn(product0.getMpn());
        product1.setMsrpCurrency(product0.getMsrpCurrency());
        product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product1.setPopular(product0.getPopular());
        product1.setProductionDate(product0.getProductionDate());
        product1.setRate(product0.getRate());
        product1.setReleaseDate(product0.getReleaseDate());
        product1.setReviews(product0.getReviews());
        product1.setSerialNumberEnd(product0.getSerialNumberEnd());
        product1.setSerialNumberStart(product0.getSerialNumberStart());
        product1.setUpdatedAt(product0.getUpdatedAt());
        product1.setId("575a96305764b195b7c0f7841");
        product1.setName("Moto 360");
        product1.setBrandID("warrantixID");
        product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product1.setCategory("appliances");
        product1.setSku("279");
        product1.setModel("WT 650 CF, Codrej");
        product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product1.setMsrp(940);
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(reviews.get(2));
        reviews1.add(reviews.get(3));
        product1.setReviews(reviews1);
        List<Rate> rates1 = new ArrayList<>();
        rates1.add(rates.get(2));
        rates1.add(rates.get(3));
        product1.setRate(rates1);

        products.add(product1);

        Product product2 = new Product();
        product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.marketplace_usedproduct_electronics1).toString());
        product2.setClass_(product0.getClass_());
        product2.setLoading(product0.getLoading());
        product2.setManualImageThmb(product0.getManualImageThmb());
        product2.setManualUrl(product0.getManualUrl());
        product2.setMpn(product0.getMpn());
        product2.setMsrpCurrency(product0.getMsrpCurrency());
        product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product2.setPopular(product0.getPopular());
        product2.setProductionDate(product0.getProductionDate());
        product2.setRate(product0.getRate());
        product2.setReleaseDate(product0.getReleaseDate());
        product2.setReviews(product0.getReviews());
        product2.setSerialNumberEnd(product0.getSerialNumberEnd());
        product2.setSerialNumberStart(product0.getSerialNumberStart());
        product2.setUpdatedAt(product0.getUpdatedAt());
        product2.setId("575a9630d5725ffc91c74eab1");
        product2.setName("Karizma ZMR");
        product2.setBrandID("warrantixID");
        product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
        product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
        product2.setCategory("electronics");
        product2.setSku("471");
        product2.setModel("Hero MotoCorp");
        product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product2.setMsrp(718);
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(reviews.get(4));
        reviews2.add(reviews.get(5));
        product2.setReviews(reviews2);
        List<Rate> rates2 = new ArrayList<>();
        rates2.add(rates.get(4));
        rates2.add(rates.get(5));
        product2.setRate(rates2);

        products.add(product2);

        Product product3 = new Product();
        product3.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.marketplace_usedproduct_electronics1).toString());
        product3.setClass_(product0.getClass_());
        product3.setLoading(product0.getLoading());
        product3.setManualImageThmb(product0.getManualImageThmb());
        product3.setManualUrl(product0.getManualUrl());
        product3.setMpn(product0.getMpn());
        product3.setMsrpCurrency(product0.getMsrpCurrency());
        product3.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product3.setPopular(product0.getPopular());
        product3.setProductionDate(product0.getProductionDate());
        product3.setRate(product0.getRate());
        product3.setReleaseDate(product0.getReleaseDate());
        product3.setReviews(product0.getReviews());
        product3.setSerialNumberEnd(product0.getSerialNumberEnd());
        product3.setSerialNumberStart(product0.getSerialNumberStart());
        product3.setUpdatedAt(product0.getUpdatedAt());
        product3.setId("575a9630ac9474317edd8aab1");
        product3.setName("Karizma ZMR");
        product3.setBrandID("warrantixID");
        product3.setDescription("Officia nostrud aute eu irure magna deserunt occaecat mollit non nostrud sint irure. Velit occaecat Lorem proident officia do nisi pariatur eiusmod proident veniam");
        product3.setShortDescription("Ut enim magna nostrud ullamco.");
        product3.setCategory("electronics");
        product3.setModel("WT 650 CF, Codrej");
        product3.setSku("499");
        product3.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product3.setMsrp(354);
        List<Review> reviews3 = new ArrayList<>();
        reviews3.add(reviews.get(6));
        reviews3.add(reviews.get(7));
        product3.setReviews(reviews3);
        List<Rate> rates3 = new ArrayList<>();
        rates3.add(rates.get(6));
        rates3.add(rates.get(7));
        product3.setRate(rates3);

        products.add(product3);

        Product product4 = new Product();
        product4.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.marketplace_usedproduct_appliance2).toString());
        product4.setClass_(product0.getClass_());
        product4.setLoading(product0.getLoading());
        product4.setManualImageThmb(product0.getManualImageThmb());
        product4.setManualUrl(product0.getManualUrl());
        product4.setMpn(product0.getMpn());
        product4.setMsrpCurrency(product0.getMsrpCurrency());
        product4.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product4.setPopular(product0.getPopular());
        product4.setProductionDate(product0.getProductionDate());
        product4.setRate(product0.getRate());
        product4.setReleaseDate(product0.getReleaseDate());
        product4.setReviews(product0.getReviews());
        product4.setSerialNumberEnd(product0.getSerialNumberEnd());
        product4.setSerialNumberStart(product0.getSerialNumberStart());
        product4.setUpdatedAt(product0.getUpdatedAt());
        product4.setId("575a963098d67ff6c6e38cc11");
        product4.setName("Hunk");
        product4.setBrandID("warrantixID");
        product4.setDescription("Eiusmod proident qui est cillum adipisicing minim culpa sunt minim duis dolor ea. Laboris elit elit consectetur tempor non pariatur nisi fugiat elit cupidatat in ea.");
        product4.setShortDescription("Enim ex est dolore commodo dolor occaecat.");
        product4.setCategory("vehicles");
        product4.setSku("323");
        product4.setModel("Hero MotoCorp");
        product4.setFeatureAndDetails("Culpa et sit ipsum et nostrud consectetur ea velit sunt. Culpa voluptate occaecat duis mollit id occaecat culpa eu duis ex cillum incididunt ullamco consectetur.");
        product4.setMsrp(147);
        List<Review> reviews4 = new ArrayList<>();
        reviews4.add(reviews.get(8));
        reviews4.add(reviews.get(9));
        product4.setReviews(reviews4);
        List<Rate> rates4 = new ArrayList<>();
        rates4.add(rates.get(8));
        rates4.add(rates.get(9));
        product4.setRate(rates4);

        products.add(product4);

        Product product5 = new Product();
        product5.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.karizma_deal).toString());
        product5.setClass_(product0.getClass_());
        product5.setLoading(product0.getLoading());
        product5.setManualImageThmb(product0.getManualImageThmb());
        product5.setManualUrl(product0.getManualUrl());
        product5.setMpn(product0.getMpn());
        product5.setMsrpCurrency(product0.getMsrpCurrency());
        product5.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product5.setPopular(product0.getPopular());
        product5.setProductionDate(product0.getProductionDate());
        product5.setRate(product0.getRate());
        product5.setReleaseDate(product0.getReleaseDate());
        product5.setReviews(product0.getReviews());
        product5.setSerialNumberEnd(product0.getSerialNumberEnd());
        product5.setSerialNumberStart(product0.getSerialNumberStart());
        product5.setUpdatedAt(product0.getUpdatedAt());
        product5.setId("575a963055b8439f41aac0ff1");
        product5.setName("Karizma ZMR");
        product5.setBrandID("warrantixID");
        product5.setDescription("Officia fugiat irure dolor in nulla esse culpa aliquip minim labore Lorem nostrud ea. Ut adipisicing exercitation est eu consectetur aliquip anim commodo deserunt sunt culpa aliquip sint.");
        product5.setShortDescription("Eu aute ut adipisicing consectetur reprehenderit voluptate pariatur.");
        product5.setCategory("vehicles");
        product5.setSku("418");
        product5.setModel("WT 650 CF, Codrej");
        product5.setFeatureAndDetails("Esse excepteur labore reprehenderit minim ullamco aliqua laboris sint sit est id. Fugiat dolore aliqua qui ut occaecat sint.");
        product5.setMsrp(531);
        List<Review> reviews5 = new ArrayList<>();
        reviews5.add(reviews.get(10));
        reviews5.add(reviews.get(11));
        product5.setReviews(reviews5);
        List<Rate> rates5 = new ArrayList<>();
        rates5.add(rates.get(10));
        rates5.add(rates.get(11));
        product5.setRate(rates5);

        products.add(product5);

        Product product6 = new Product();
        product6.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.karizma_deal).toString());
        product6.setClass_(product0.getClass_());
        product6.setLoading(product0.getLoading());
        product6.setManualImageThmb(product0.getManualImageThmb());
        product6.setManualUrl(product0.getManualUrl());
        product6.setMpn(product0.getMpn());
        product6.setMsrpCurrency(product0.getMsrpCurrency());
        product6.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product6.setPopular(product0.getPopular());
        product6.setProductionDate(product0.getProductionDate());
        product6.setRate(product0.getRate());
        product6.setReleaseDate(product0.getReleaseDate());
        product6.setReviews(product0.getReviews());
        product6.setSerialNumberEnd(product0.getSerialNumberEnd());
        product6.setSerialNumberStart(product0.getSerialNumberStart());
        product6.setUpdatedAt(product0.getUpdatedAt());
        product6.setId("575a96305764b195b7c0f78411");
        product6.setName("Karizma ZMR");
        product6.setBrandID("warrantixID");
        product6.setDescription("Est laborum minim sit laboris cupidatat labore do. Dolor in veniam ut ad enim mollit esse non deserunt occaecat.");
        product6.setShortDescription("Est do ullamco ex eiusmod labore nisi nisi ipsum sunt est reprehenderit ea enim.");
        product6.setCategory("fitness");
        product6.setSku("722");
        product6.setModel("Hero MotoCorp");
        product6.setFeatureAndDetails("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
        product6.setMsrp(647);
        List<Review> reviews6 = new ArrayList<>();
        reviews6.add(reviews.get(12));
        reviews6.add(reviews.get(13));
        product6.setReviews(reviews6);
        List<Rate> rates6 = new ArrayList<>();
        rates6.add(rates.get(12));
        rates6.add(rates.get(13));
        product6.setRate(rates6);

        products.add(product6);

        Product product7 = new Product();
        product7.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.hunk_deal).toString());
        product7.setClass_(product0.getClass_());
        product7.setLoading(product0.getLoading());
        product7.setManualImageThmb(product0.getManualImageThmb());
        product7.setManualUrl(product0.getManualUrl());
        product7.setMpn(product0.getMpn());
        product7.setMsrpCurrency(product0.getMsrpCurrency());
        product7.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product7.setPopular(product0.getPopular());
        product7.setProductionDate(product0.getProductionDate());
        product7.setRate(product0.getRate());
        product7.setReleaseDate(product0.getReleaseDate());
        product7.setReviews(product0.getReviews());
        product7.setSerialNumberEnd(product0.getSerialNumberEnd());
        product7.setSerialNumberStart(product0.getSerialNumberStart());
        product7.setUpdatedAt(product0.getUpdatedAt());
        product7.setId("575a9630d5725ffc91c74eab11");
        product7.setName("Hunk");
        product7.setBrandID("warrantixID");
        product7.setDescription("Aliqua cillum aute deserunt Lorem mollit elit ipsum ut nulla sit enim tempor esse. Ea laborum proident ad irure tempor reprehenderit enim excepteur aliqua.");
        product7.setShortDescription("Adipisicing anim aliquip elit dolor sint.");
        product7.setCategory("fitness");
        product7.setSku("77");
        product7.setModel("WT 650 CF, Codrej");
        product7.setFeatureAndDetails("Mollit tempor voluptate tempor laboris ex aute tempor minim enim officia sint consectetur do. Proident Lorem nostrud incididunt duis dolor aliquip est amet.");
        product7.setMsrp(865);
        List<Review> reviews7 = new ArrayList<>();
        reviews7.add(reviews.get(14));
        reviews7.add(reviews.get(15));
        product7.setReviews(reviews7);
        List<Rate> rates7 = new ArrayList<>();
        rates7.add(rates.get(14));
        rates7.add(rates.get(15));
        product7.setRate(rates7);

        products.add(product7);

        Product product8 = new Product();
        product8.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.karizma_deal).toString());
        product8.setClass_(product0.getClass_());
        product8.setLoading(product0.getLoading());
        product8.setManualImageThmb(product0.getManualImageThmb());
        product8.setManualUrl(product0.getManualUrl());
        product8.setMpn(product0.getMpn());
        product8.setMsrpCurrency(product0.getMsrpCurrency());
        product8.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product8.setPopular(product0.getPopular());
        product8.setProductionDate(product0.getProductionDate());
        product8.setRate(product0.getRate());
        product8.setReleaseDate(product0.getReleaseDate());
        product8.setReviews(product0.getReviews());
        product8.setSerialNumberEnd(product0.getSerialNumberEnd());
        product8.setSerialNumberStart(product0.getSerialNumberStart());
        product8.setUpdatedAt(product0.getUpdatedAt());
        product8.setId("575a9630ac9474317edd8aab11");
        product8.setName("Karizma ZMR");
        product8.setBrandID("warrantixID");
        product8.setDescription("Laborum do id occaecat exercitation occaecat quis esse aute nostrud est aliqua. Labore ea cillum ut pariatur.");
        product8.setShortDescription("Incididunt cupidatat et consectetur commodo laboris est non.");
        product8.setCategory("personal");
        product8.setSku("701");
        product8.setModel("Hero MotoCorp");
        product8.setFeatureAndDetails("Exercitation adipisicing adipisicing aute sit Lorem tempor labore anim deserunt deserunt mollit officia et. Occaecat id sit ut cupidatat duis nostrud esse amet eu.");
        product8.setMsrp(117);
        List<Review> reviews8 = new ArrayList<>();
        reviews8.add(reviews.get(16));
        reviews8.add(reviews.get(17));
        product8.setReviews(reviews8);
        List<Rate> rates8 = new ArrayList<>();
        rates8.add(rates.get(16));
        rates8.add(rates.get(17));
        product8.setRate(rates8);

        products.add(product8);

        return products;
    }

    public static Product getProduct (String productId, String category){
        List<Product> products = new ArrayList<>();
        Product selProduct = new Product();
        if (category == null) {
            products = createProducts();
        } else {
            products = createProductsForWarrantixUsedProduct();
        }
        for (int i = 0; i < products.size(); i++){
            Product product = products.get(i);
            String pId = product.getId();
            if (pId.equals(productId)){
                selProduct = product;
                break;
            }
        }
        return selProduct;
    }

    public synchronized static List<Product> createPopularProducts(){
        List<Product> products = new ArrayList<>();

        List<Review> reviews = new ArrayList<>();
        reviews = createReviews();

        List<Rate> rates = new ArrayList<>();
        rates = createRates();

        Product product0 = new Product();
        product0.setId("575a963055b8439f41aac0ff1");
        product0.setName("Sport Bike");
        product0.setCreatedAt("2016-06-10");
        product0.setUpdatedAt("2016-06-10");
        product0.setBrandID("heroID");
        product0.setClass_("150cc");
        product0.setCategory("appliances");
        product0.setModel("Hero MotoCorp");
        product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
        product0.setSerialNumberStart("string");
        product0.setSerialNumberEnd("string");
        product0.setLoading("string");
        product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
        product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
        product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.sport_bike).toString());
        product0.setMpn("string");
        product0.setProductionDate("2016-06-10");
        product0.setReleaseDate("2016-06-10");
        product0.setSku("690");
        product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
        product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
        product0.setMsrp(150000);
        MsrpCurrency msrpCurrency0 = new MsrpCurrency();
        msrpCurrency0.setMsrpCurrency("INR");
        product0.setMsrpCurrency(msrpCurrency0);
        product0.setMsrpCurrencySymb("&#8377;");
        product0.setFeatured(false);
        product0.setPopular(false);

        List<Review> reviews0 = new ArrayList<>();
        reviews0.add(reviews.get(0));
        reviews0.add(reviews.get(1));
        reviews0.add(reviews.get(2));
        reviews0.add(reviews.get(3));
        product0.setReviews(reviews0);

        List<Rate> rates0 = new ArrayList<>();
        rates0.add(rates.get(0));
        rates0.add(rates.get(1));
        rates0.add(rates.get(2));
        rates0.add(rates.get(3));
        product0.setRate(rates0);

        products.add(product0);

        Product product1 = new Product();
        product1.setClass_(product0.getClass_());
        product1.setLoading(product0.getLoading());
        product1.setManualImageThmb(product0.getManualImageThmb());
        product1.setManualUrl(product0.getManualUrl());
        product1.setMpn(product0.getMpn());
        product1.setMsrpCurrency(product0.getMsrpCurrency());
        product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product1.setPopular(product0.getPopular());
        product1.setProductionDate(product0.getProductionDate());
        product1.setRate(product0.getRate());
        product1.setReleaseDate(product0.getReleaseDate());
        product1.setReviews(product0.getReviews());
        product1.setSerialNumberEnd(product0.getSerialNumberEnd());
        product1.setSerialNumberStart(product0.getSerialNumberStart());
        product1.setUpdatedAt(product0.getUpdatedAt());
        product1.setId("575a96305764b195b7c0f7841");
        product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.motocycle_bike).toString());
        product1.setName("Motocycle");
        product1.setBrandID("heroID");
        product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product1.setCategory("appliances");
        product1.setSku("279");
        product1.setModel("WT 650 CF, Codrej");
        product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product1.setMsrp(80000);
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(reviews.get(2));
        reviews1.add(reviews.get(3));
        product1.setReviews(reviews1);
        List<Rate> rates1 = new ArrayList<>();
        rates1.add(rates.get(2));
        rates1.add(rates.get(3));
        product1.setRate(rates1);

        products.add(product1);

        Product product2 = new Product();
        product2.setClass_(product0.getClass_());
        product2.setLoading(product0.getLoading());
        product2.setManualImageThmb(product0.getManualImageThmb());
        product2.setManualUrl(product0.getManualUrl());
        product2.setMpn(product0.getMpn());
        product2.setMsrpCurrency(product0.getMsrpCurrency());
        product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product2.setPopular(product0.getPopular());
        product2.setProductionDate(product0.getProductionDate());
        product2.setRate(product0.getRate());
        product2.setReleaseDate(product0.getReleaseDate());
        product2.setReviews(product0.getReviews());
        product2.setSerialNumberEnd(product0.getSerialNumberEnd());
        product2.setSerialNumberStart(product0.getSerialNumberStart());
        product2.setUpdatedAt(product0.getUpdatedAt());
        product2.setId("575a9630d5725ffc91c74eab1");
        product2.setName("Scooters");
        product2.setBrandID("heroID");
        product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
        product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
        product2.setCategory("electronics");
        product2.setSku("471");
        product2.setModel("Hero MotoCorp");
        product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.scooter_bike).toString());
        product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product2.setMsrp(150000);
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(reviews.get(4));
        reviews2.add(reviews.get(5));
        product2.setReviews(reviews2);
        List<Rate> rates2 = new ArrayList<>();
        rates2.add(rates.get(2));
        rates2.add(rates.get(1));
        product2.setRate(rates2);

        products.add(product2);


        return products;
    }

    // create the products for top rate.

    public synchronized static List<Product> createTopRateProducts(){
        List<Product> products = new ArrayList<>();

        List<Review> reviews = new ArrayList<>();
        reviews = createReviews();

        List<Rate> rates = new ArrayList<>();
        rates = createRates();

        Product product0 = new Product();
        product0.setId("575a963055b8439f41aac0ff1");
        product0.setName("Karima ZMR");
        product0.setCreatedAt("2016-06-10");
        product0.setUpdatedAt("2016-06-10");
        product0.setBrandID("heroID");
        product0.setClass_("150cc");
        product0.setCategory("appliances");
        product0.setModel("Hero MotoCorp");
        product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
        product0.setSerialNumberStart("string");
        product0.setSerialNumberEnd("string");
        product0.setLoading("string");
        product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
        product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
        product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.karizma_deal).toString());
        product0.setMpn("string");
        product0.setProductionDate("2016-06-10");
        product0.setReleaseDate("2016-06-10");
        product0.setSku("690");
        product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
        product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
        product0.setMsrp(150000);
        MsrpCurrency msrpCurrency0 = new MsrpCurrency();
        msrpCurrency0.setMsrpCurrency("INR");
        product0.setMsrpCurrency(msrpCurrency0);
        product0.setMsrpCurrencySymb("&#8377;");
        product0.setFeatured(false);
        product0.setPopular(false);
        List<Review> reviews0 = new ArrayList<>();
        reviews0.add(reviews.get(0));
        reviews0.add(reviews.get(1));
        reviews0.add(reviews.get(2));
        reviews0.add(reviews.get(3));
        product0.setReviews(reviews0);
        List<Rate> rates0 = new ArrayList<>();
        rates0.add(rates.get(0));
        rates0.add(rates.get(1));
        product0.setRate(rates0);

        products.add(product0);

        Product product1 = new Product();
        product1.setClass_(product0.getClass_());
        product1.setLoading(product0.getLoading());
        product1.setManualImageThmb(product0.getManualImageThmb());
        product1.setManualUrl(product0.getManualUrl());
        product1.setMpn(product0.getMpn());
        product1.setMsrpCurrency(product0.getMsrpCurrency());
        product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product1.setPopular(product0.getPopular());
        product1.setProductionDate(product0.getProductionDate());
        product1.setRate(product0.getRate());
        product1.setReleaseDate(product0.getReleaseDate());
        product1.setReviews(product0.getReviews());
        product1.setSerialNumberEnd(product0.getSerialNumberEnd());
        product1.setSerialNumberStart(product0.getSerialNumberStart());
        product1.setUpdatedAt(product0.getUpdatedAt());
        product1.setId("575a96305764b195b7c0f7841");
        product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.ignitor).toString());
        product1.setName("Ignitor");
        product1.setBrandID("heroID");
        product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product1.setCategory("appliances");
        product1.setSku("279");
        product1.setModel("WT 650 CF, Codrej");
        product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product1.setMsrp(80000);
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(reviews.get(2));
        reviews1.add(reviews.get(3));
        product1.setReviews(reviews1);
        List<Rate> rates1 = new ArrayList<>();
        rates1.add(rates.get(2));
        rates1.add(rates.get(3));
        product1.setRate(rates1);

        products.add(product1);

        Product product2 = new Product();
        product2.setClass_(product0.getClass_());
        product2.setLoading(product0.getLoading());
        product2.setManualImageThmb(product0.getManualImageThmb());
        product2.setManualUrl(product0.getManualUrl());
        product2.setMpn(product0.getMpn());
        product2.setMsrpCurrency(product0.getMsrpCurrency());
        product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product2.setPopular(product0.getPopular());
        product2.setProductionDate(product0.getProductionDate());
        product2.setRate(product0.getRate());
        product2.setReleaseDate(product0.getReleaseDate());
        product2.setReviews(product0.getReviews());
        product2.setSerialNumberEnd(product0.getSerialNumberEnd());
        product2.setSerialNumberStart(product0.getSerialNumberStart());
        product2.setUpdatedAt(product0.getUpdatedAt());
        product2.setId("575a9630d5725ffc91c74eab1");
        product2.setName("Karizma ZMR");
        product2.setBrandID("heroID");
        product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
        product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
        product2.setCategory("electronics");
        product2.setSku("471");
        product2.setModel("Hero MotoCorp");
        product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.karizma_deal).toString());
        product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product2.setMsrp(150000);
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(reviews.get(4));
        reviews2.add(reviews.get(5));
        product2.setReviews(reviews2);
        List<Rate> rates2 = new ArrayList<>();
        rates2.add(rates.get(2));
        rates2.add(rates.get(3));
        product2.setRate(rates2);

        products.add(product2);


        return products;
    }

    // get the products for best selling
    public synchronized static List<Product> createBestSellingProducts(){
        List<Product> products = new ArrayList<>();

        List<Review> reviews = new ArrayList<>();
        reviews = createReviews();

        List<Rate> rates = new ArrayList<>();
        rates = createRates();

        Product product0 = new Product();
        product0.setId("575a963055b8439f41aac0ff1");
        product0.setName("Karima ZMR");
        product0.setCreatedAt("2016-06-10");
        product0.setUpdatedAt("2016-06-10");
        product0.setBrandID("heroID");
        product0.setClass_("150cc");
        product0.setCategory("appliances");
        product0.setModel("Hero MotoCorp");
        product0.setShortDescription("Culpa excepteur culpa sunt irure reprehenderit sit non aliqua enim dolore tempor.");
        product0.setSerialNumberStart("string");
        product0.setSerialNumberEnd("string");
        product0.setLoading("string");
        product0.setManualUrl("http://egalo.com/guitar-fretboard-visualization-chart-with-note-names.pdf");
        product0.setManualImageThmb("http://www.heromotocorp.com/en-in/images/warranty-banner.jpg");
        product0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.karizma_deal).toString());
        product0.setMpn("string");
        product0.setProductionDate("2016-06-10");
        product0.setReleaseDate("2016-06-10");
        product0.setSku("690");
        product0.setDescription("Nulla enim ad id ullamco ad anim incididunt Lorem magna nostrud. Minim ullamco eiusmod amet eiusmod irure ipsum cupidatat excepteur culpa quis.");
        product0.setFeatureAndDetails("Enim ea voluptate et eiusmod laborum tempor Lorem. Et veniam consequat duis qui et ipsum.");
        product0.setMsrp(150000);
        MsrpCurrency msrpCurrency0 = new MsrpCurrency();
        msrpCurrency0.setMsrpCurrency("INR");
        product0.setMsrpCurrency(msrpCurrency0);
        product0.setMsrpCurrencySymb("&#8377;");
        product0.setFeatured(false);
        product0.setPopular(false);
        List<Review> reviews0 = new ArrayList<>();
        reviews0.add(reviews.get(0));
        reviews0.add(reviews.get(1));
        reviews0.add(reviews.get(2));
        reviews0.add(reviews.get(3));
        product0.setReviews(reviews0);
        List<Rate> rates0 = new ArrayList<>();
        rates0.add(rates.get(0));
        rates0.add(rates.get(1));
        rates0.add(rates.get(2));
        rates0.add(rates.get(3));
        product0.setRate(rates0);

        products.add(product0);

        Product product1 = new Product();
        product1.setClass_(product0.getClass_());
        product1.setLoading(product0.getLoading());
        product1.setManualImageThmb(product0.getManualImageThmb());
        product1.setManualUrl(product0.getManualUrl());
        product1.setMpn(product0.getMpn());
        product1.setMsrpCurrency(product0.getMsrpCurrency());
        product1.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product1.setPopular(product0.getPopular());
        product1.setProductionDate(product0.getProductionDate());
        product1.setRate(product0.getRate());
        product1.setReleaseDate(product0.getReleaseDate());
        product1.setReviews(product0.getReviews());
        product1.setSerialNumberEnd(product0.getSerialNumberEnd());
        product1.setSerialNumberStart(product0.getSerialNumberStart());
        product1.setUpdatedAt(product0.getUpdatedAt());
        product1.setId("575a96305764b195b7c0f7841");
        product1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.hunk).toString());
        product1.setName("Hunk");
        product1.setBrandID("heroID");
        product1.setDescription("Veniam ipsum officia quis id. Consectetur qui est id irure magna velit ex.");
        product1.setShortDescription("Do ullamco velit aliquip sit proident cillum.");
        product1.setCategory("appliances");
        product1.setSku("279");
        product1.setModel("WT 650 CF, Codrej");
        product1.setFeatureAndDetails("Do enim ut dolore aliquip dolor in id officia dolor eiusmod. Culpa laboris nulla sint Lorem et esse sint eu cillum eiusmod mollit enim.");
        product1.setMsrp(80000);
        List<Review> reviews1 = new ArrayList<>();
        reviews1.add(reviews.get(2));
        reviews1.add(reviews.get(3));
        product1.setReviews(reviews1);
        List<Rate> rates1 = new ArrayList<>();
        rates1.add(rates.get(2));
        rates1.add(rates.get(3));
        product1.setRate(rates1);

        products.add(product1);

        Product product2 = new Product();
        product2.setClass_(product0.getClass_());
        product2.setLoading(product0.getLoading());
        product2.setManualImageThmb(product0.getManualImageThmb());
        product2.setManualUrl(product0.getManualUrl());
        product2.setMpn(product0.getMpn());
        product2.setMsrpCurrency(product0.getMsrpCurrency());
        product2.setMsrpCurrencySymb(product0.getMsrpCurrencySymb());
        product2.setPopular(product0.getPopular());
        product2.setProductionDate(product0.getProductionDate());
        product2.setRate(product0.getRate());
        product2.setReleaseDate(product0.getReleaseDate());
        product2.setReviews(product0.getReviews());
        product2.setSerialNumberEnd(product0.getSerialNumberEnd());
        product2.setSerialNumberStart(product0.getSerialNumberStart());
        product2.setUpdatedAt(product0.getUpdatedAt());
        product2.setId("575a9630d5725ffc91c74eab1");
        product2.setName("Karizma ZMR");
        product2.setBrandID("heroID");
        product2.setDescription("Excepteur dolore eu esse magna officia excepteur. Minim commodo aute est eu occaecat ipsum amet laboris magna duis ullamco nostrud.");
        product2.setShortDescription("Cupidatat officia labore mollit nisi pariatur non aliquip sit ullamco adipisicing laborum anim in tempor.");
        product2.setCategory("electronics");
        product2.setSku("471");
        product2.setModel("Hero MotoCorp");
        product2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/"+ R.drawable.karizma_deal).toString());
        product2.setFeatureAndDetails("Anim qui amet eiusmod sunt ipsum. Excepteur esse ex officia amet exercitation.");
        product2.setMsrp(150000);
        List<Review> reviews2 = new ArrayList<>();
        reviews2.add(reviews.get(2));
        reviews2.add(reviews.get(3));
        product2.setReviews(reviews2);
        List<Rate> rates2 = new ArrayList<>();
        rates2.add(rates.get(2));
        rates2.add(rates.get(3));
        product2.setRate(rates2);

        products.add(product2);


        return products;
    }

    // create the mock review list
    public synchronized  static List<Review> createReviews (){

        if (mockReviews.size() == 0) {

            Review review0 = new Review();
            review0.setCustomerID("c0");
            review0.setReview("Aliqua anim ullamco veniam laborum ut minim deserunt tempor adipisicing ad velit occaecat.");

            mockReviews.add(review0);

            Review review1 = new Review();
            review1.setCustomerID("c1");
            review1.setReview("Sunt nisi reprehenderit sunt veniam minim.");

            mockReviews.add(review1);

            Review review2 = new Review();
            review2.setCustomerID("c2");
            review2.setReview("Occaecat ut consequat aliquip esse et aliquip excepteur ex aute dolor ex proident.");

            mockReviews.add(review2);

            Review review3 = new Review();
            review3.setCustomerID("c3");
            review3.setReview("Duis consequat deserunt consequat ipsum proident officia quis nulla occaecat minim sint est.");

            mockReviews.add(review3);

            Review review4 = new Review();
            review4.setCustomerID("c0");
            review4.setReview("Irure sit quis est amet tempor voluptate sint in Lorem.");

            mockReviews.add(review4);

            Review review5 = new Review();
            review5.setCustomerID("c1");
            review5.setReview("Sint sit laborum mollit nisi anim non velit in incididunt velit do consequat aliquip.");

            mockReviews.add(review5);

            Review review6 = new Review();
            review6.setCustomerID("c2");
            review6.setReview("Commodo duis quis ullamco nostrud esse consectetur nostrud dolore incididunt eiusmod quis anim nulla.");

            mockReviews.add(review6);

            Review review7 = new Review();
            review7.setCustomerID("c3");
            review7.setReview("Ipsum cillum deserunt nostrud incididunt officia duis labore commodo non tempor eiusmod nisi labore officia.");

            mockReviews.add(review7);

            Review review8 = new Review();
            review8.setCustomerID("c0");
            review8.setReview("Amet eu non adipisicing anim.");

            mockReviews.add(review8);

            Review review9 = new Review();
            review9.setCustomerID("c1");
            review9.setReview("Ex consequat mollit elit exercitation voluptate esse do.");

            mockReviews.add(review9);

            Review review10 = new Review();
            review10.setCustomerID("c2");
            review10.setReview("Cillum et est id sunt eu sit laborum tempor.");

            mockReviews.add(review10);

            Review review11 = new Review();
            review11.setCustomerID("c3");
            review11.setReview("Duis enim cupidatat quis voluptate anim elit adipisicing magna magna ullamco.");

            mockReviews.add(review11);

            Review review12 = new Review();
            review12.setCustomerID("c0");
            review12.setReview("Minim consequat non ea dolore ipsum eiusmod excepteur cillum.");

            mockReviews.add(review12);

            Review review13 = new Review();
            review13.setCustomerID("c1");
            review13.setReview("Est nostrud anim ullamco proident qui irure duis commodo.");

            mockReviews.add(review13);

            Review review14 = new Review();
            review14.setCustomerID("c2");
            review14.setReview("Et voluptate duis ea nulla quis id labore cupidatat incididunt.");

            mockReviews.add(review14);

            Review review15 = new Review();
            review15.setCustomerID("c3");
            review15.setReview("Veniam esse anim pariatur ullamco fugiat aute qui ut deserunt cillum do occaecat duis.");

            mockReviews.add(review15);

            Review review16 = new Review();
            review16.setCustomerID("c0");
            review16.setReview("Proident ex minim ad mollit sit deserunt eiusmod duis ut dolor in aliqua pariatur.");

            mockReviews.add(review16);

            Review review17 = new Review();
            review17.setCustomerID("c1");
            review17.setReview("Quis mollit duis consequat cillum qui id et id qui.");

            mockReviews.add(review17);

            Review review18 = new Review();
            review18.setCustomerID("c2");
            review18.setReview("Et eiusmod Lorem ex voluptate duis consectetur aliqua pariatur commodo irure est.");

            mockReviews.add(review18);

            Review review19 = new Review();
            review19.setCustomerID("c1");
            review19.setReview("Aliqua deserunt velit magna mollit reprehenderit dolore veniam nisi cillum non cupidatat fugiat eu.");

            mockReviews.add(review19);
        }

        return mockReviews;
    }

    // create the mock rate list
    public static List<Rate> createRates (){

        if (mockRates.size() == 0) {

            Rate rate0 = new Rate();
            rate0.setCustomerID("c0");
            rate0.setRate(2);

            mockRates.add(rate0);

            Rate rate1 = new Rate();
            rate1.setCustomerID("c1");
            rate1.setRate(4);

            mockRates.add(rate1);

            Rate rate2 = new Rate();
            rate2.setCustomerID("c2");
            rate2.setRate(3);

            mockRates.add(rate2);

            Rate rate3 = new Rate();
            rate3.setCustomerID("c3");
            rate3.setRate(4);

            mockRates.add(rate3);

            Rate rate4 = new Rate();
            rate4.setCustomerID("c0");
            rate4.setRate(5);

            mockRates.add(rate4);

            Rate rate5 = new Rate();
            rate5.setCustomerID("c1");
            rate5.setRate(1);

            mockRates.add(rate5);

            Rate rate6 = new Rate();
            rate6.setCustomerID("c2");
            rate6.setRate(2);

            mockRates.add(rate6);

            Rate rate7 = new Rate();
            rate7.setCustomerID("c3");
            rate7.setRate(3);

            mockRates.add(rate7);

            Rate rate8 = new Rate();
            rate8.setCustomerID("c0");
            rate8.setRate(5);

            mockRates.add(rate8);

            Rate rate9 = new Rate();
            rate9.setCustomerID("c1");
            rate9.setRate(1);

            mockRates.add(rate9);

            Rate rate10 = new Rate();
            rate10.setCustomerID("c2");
            rate10.setRate(4);

            mockRates.add(rate10);

            Rate rate11 = new Rate();
            rate11.setCustomerID("c3");
            rate11.setRate(3);

            mockRates.add(rate11);

            Rate rate12 = new Rate();
            rate12.setCustomerID("c0");
            rate12.setRate(1);

            mockRates.add(rate12);

            Rate rate13 = new Rate();
            rate13.setCustomerID("c1");
            rate13.setRate(5);

            mockRates.add(rate13);

            Rate rate14 = new Rate();
            rate14.setCustomerID("c2");
            rate14.setRate(2);

            mockRates.add(rate14);

            Rate rate15 = new Rate();
            rate15.setCustomerID("c3");
            rate15.setRate(1);

            mockRates.add(rate15);

            Rate rate16 = new Rate();
            rate16.setCustomerID("c0");
            rate16.setRate(5);

            mockRates.add(rate16);

            Rate rate17 = new Rate();
            rate17.setCustomerID("c1");
            rate17.setRate(1);

            mockRates.add(rate17);

            Rate rate18 = new Rate();
            rate18.setCustomerID("c2");
            rate18.setRate(2);

            mockRates.add(rate18);

            Rate rate19 = new Rate();
            rate19.setCustomerID("c3");
            rate19.setRate(2);

            mockRates.add(rate19);
        }

        return mockRates;
    }

    public static List<RelatedProduct> createRelatedProducts() {

        if (mockRelateProducts.size() == 0 ) {

            RelatedProduct relatedProduct0 = new RelatedProduct();
            relatedProduct0.setFeatured(true);
            relatedProduct0.setProductID("575a963055b8439f41aac0ff");
            relatedProduct0.setDescription("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
            relatedProduct0.setMsrpCurrencySymb("aeiou");
            relatedProduct0.setSerialNumberEnd("aeiou");
            relatedProduct0.setCreatedAt("2000-01-23T04:56:07.000+0000");
            relatedProduct0.setProductionDate("2000-01-23T04:56:07.000+0000");
            relatedProduct0.setBrandID("aeiou");
            relatedProduct0.setMsrp(179);
            relatedProduct0.setModel("Hero Motocyles");
            relatedProduct0.setManualUrl("aeiou");
            relatedProduct0.setSku("aeiou");
            relatedProduct0.setClass_("aeiou");
            relatedProduct0.setPopular(true);
            relatedProduct0.setUpdatedAt("2000-01-23T04:56:07.000+0000");
            relatedProduct0.setSerialNumberStart("aeiou");
            relatedProduct0.setReleaseDate("2000-01-23T04:56:07.000+0000");
            relatedProduct0.setMpn("aeiou");
            relatedProduct0.setShortDescription("aeiou");
            relatedProduct0.setFeatureAndDetails("Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.");
            relatedProduct0.setLoading("aeiou");
            relatedProduct0.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            relatedProduct0.setName("Flip Cover - Galaxy 56  Edge");
            relatedProduct0.setId("aeiou");
            relatedProduct0.setCategory("category0");
            MsrpCurrency msrpCurrency = new MsrpCurrency();
            msrpCurrency.setMsrpCurrency("aeiou");
            relatedProduct0.setMsrpCurrency(msrpCurrency);

            mockRelateProducts.add(relatedProduct0);

            RelatedProduct relatedProduct1 = new RelatedProduct();
            relatedProduct1.setFeatured(relatedProduct0.getFeatured());
            relatedProduct1.setDescription(relatedProduct0.getDescription());
            relatedProduct1.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
            relatedProduct1.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
            relatedProduct1.setCreatedAt(relatedProduct0.getCreatedAt());
            relatedProduct1.setProductionDate(relatedProduct0.getProductionDate());
            relatedProduct1.setBrandID(relatedProduct0.getBrandID());
            relatedProduct1.setMsrp(relatedProduct0.getMsrp());
            relatedProduct1.setModel("Bajaj Pulsar 135/150/180");
            relatedProduct1.setManualUrl(relatedProduct0.getManualUrl());
            relatedProduct1.setSku(relatedProduct0.getSku());
            relatedProduct1.setShortDescription(relatedProduct0.getShortDescription());
            relatedProduct1.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
            relatedProduct1.setLoading(relatedProduct0.getLoading());
            relatedProduct1.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            relatedProduct1.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
            relatedProduct1.setId("aeiou1");
            relatedProduct1.setName("aeiou1");
            relatedProduct1.setProductID("575a96305764b195b7c0f784");
            relatedProduct1.setCategory("category1");

            mockRelateProducts.add(relatedProduct1);

            RelatedProduct relatedProduct2 = new RelatedProduct();
            relatedProduct2.setFeatured(relatedProduct0.getFeatured());
            relatedProduct2.setDescription(relatedProduct0.getDescription());
            relatedProduct2.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
            relatedProduct2.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
            relatedProduct2.setCreatedAt(relatedProduct0.getCreatedAt());
            relatedProduct2.setProductionDate(relatedProduct0.getProductionDate());
            relatedProduct2.setBrandID(relatedProduct0.getBrandID());
            relatedProduct2.setMsrp(relatedProduct0.getMsrp());
            relatedProduct2.setModel("Bullet(Classic)");
            relatedProduct2.setManualUrl(relatedProduct0.getManualUrl());
            relatedProduct2.setSku(relatedProduct0.getSku());
            relatedProduct2.setShortDescription(relatedProduct0.getShortDescription());
            relatedProduct2.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
            relatedProduct2.setLoading(relatedProduct0.getLoading());
            relatedProduct2.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_1).toString());
            relatedProduct2.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
            relatedProduct2.setId("aeiou2");
            relatedProduct2.setName("aeiou2");
            relatedProduct2.setProductID("575a9630d5725ffc91c74eab");
            relatedProduct2.setCategory("category2");

            mockRelateProducts.add(relatedProduct2);

            RelatedProduct relatedProduct3 = new RelatedProduct();
            relatedProduct3.setFeatured(relatedProduct0.getFeatured());
            relatedProduct3.setDescription(relatedProduct0.getDescription());
            relatedProduct3.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
            relatedProduct3.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
            relatedProduct3.setCreatedAt(relatedProduct0.getCreatedAt());
            relatedProduct3.setProductionDate(relatedProduct0.getProductionDate());
            relatedProduct3.setBrandID(relatedProduct0.getBrandID());
            relatedProduct3.setMsrp(relatedProduct0.getMsrp());
            relatedProduct3.setModel("Arai Helmets");
            relatedProduct3.setManualUrl(relatedProduct0.getManualUrl());
            relatedProduct3.setSku(relatedProduct0.getSku());
            relatedProduct3.setShortDescription(relatedProduct0.getShortDescription());
            relatedProduct3.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
            relatedProduct3.setLoading(relatedProduct0.getLoading());
            relatedProduct3.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            relatedProduct3.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
            relatedProduct3.setId("aeiou3");
            relatedProduct3.setName("aeiou3");
            relatedProduct3.setProductID("575a9630ac9474317edd8aab");
            relatedProduct3.setCategory("category1");

            mockRelateProducts.add(relatedProduct3);

            RelatedProduct relatedProduct4 = new RelatedProduct();
            relatedProduct4.setFeatured(relatedProduct0.getFeatured());
            relatedProduct4.setDescription(relatedProduct0.getDescription());
            relatedProduct4.setMsrpCurrencySymb(relatedProduct0.getMsrpCurrencySymb());
            relatedProduct4.setSerialNumberEnd(relatedProduct0.getSerialNumberEnd());
            relatedProduct4.setCreatedAt(relatedProduct0.getCreatedAt());
            relatedProduct4.setProductionDate(relatedProduct0.getProductionDate());
            relatedProduct4.setBrandID(relatedProduct0.getBrandID());
            relatedProduct4.setMsrp(relatedProduct0.getMsrp());
            relatedProduct4.setModel(relatedProduct0.getModel());
            relatedProduct4.setManualUrl(relatedProduct0.getManualUrl());
            relatedProduct4.setSku(relatedProduct0.getSku());
            relatedProduct4.setShortDescription(relatedProduct0.getShortDescription());
            relatedProduct4.setFeatureAndDetails(relatedProduct0.getFeatureAndDetails());
            relatedProduct4.setLoading(relatedProduct0.getLoading());
            relatedProduct4.setImageThmb(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.wallet_product_2).toString());
            relatedProduct4.setMsrpCurrency(relatedProduct0.getMsrpCurrency());
            relatedProduct4.setId("aeiou4");
            relatedProduct4.setName("aeiou4");
            relatedProduct4.setProductID("575a963098d67ff6c6e38cc9");
            relatedProduct4.setCategory("category0");

            mockRelateProducts.add(relatedProduct4);

        }

        return mockRelateProducts;
    }


    public static List<ServiceCompany> createFinanceServiceCompanies () {

        List<ServiceCompany> serviceCompanies = new ArrayList<>();

        ServiceCompany serviceCompany0 = new ServiceCompany();
        serviceCompany0.setName("Hero MotoCorp");
        serviceCompany0.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.hero3).toString());
        serviceCompany0.setDescription("Comprehensive, Starts form Rs. 4000 p.a");
        serviceCompany0.setId("575acf2274550a837e5d9c92");
        serviceCompany0.setCreatedAt("2016-05-25");
        serviceCompany0.setUpdatedAt("2016-05-25");
        Contact contact0 = new Contact();
        contact0.setFirstName("Matthews");
        contact0.setLastName("Barlow");
        contact0.setTel("+86 (182) 4255-0289");
        contact0.setSsn("Gale");
        contact0.setAddress("757 Gallatin Place");
        contact0.setCity("Frystown");
        contact0.setZip(86351);
        contact0.setCountry("Faroe Islands");
        contact0.setContactEmail("galebarlow@visualix.com");
        contact0.setWebsite("Silvia.com");
        contact0.setLatitude(66.206727);
        contact0.setLongitude(85.51726);
        serviceCompany0.setContact(contact0);

        serviceCompanies.add(serviceCompany0);

        ServiceCompany serviceCompany1 = new ServiceCompany();
        serviceCompany1.setName("Godrej");
        serviceCompany1.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.finance_muthoot).toString());
        serviceCompany1.setDescription("Comprehensive, Starts form Rs. 3800 p.a");
        serviceCompany1.setId("575acf2275674a353e016eb1");
        serviceCompany1.setCreatedAt("2016-05-25");
        serviceCompany1.setUpdatedAt("2016-05-25");
        Contact contact1 = new Contact();
        contact1.setFirstName("Elliott");
        contact1.setLastName("Sloan");
        contact1.setTel("+91 (987) 519-2511");
        contact1.setSsn("Esmeralda");
        contact1.setAddress("689 Surf Avenue");
        contact1.setCity("Fedora");
        contact1.setZip(42554);
        contact1.setCountry("Swaziland");
        contact1.setContactEmail("esmeraldasloan@visualix.com");
        contact1.setWebsite("Miranda.com");
        contact1.setLatitude(74.325343);
        contact1.setLongitude(27.392789);
        serviceCompany0.setContact(contact1);

        serviceCompanies.add(serviceCompany1);

        ServiceCompany serviceCompany2 = new ServiceCompany();
        serviceCompany2.setName("Hero MotoCorp1");
        serviceCompany2.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.finance_aditya).toString());
        serviceCompany2.setDescription("Comprehensive, Starts form Rs. 4500 p.a");
        serviceCompany2.setId("575acf22b15e97e514ee09c9");
        serviceCompany2.setCreatedAt("2016-05-25");
        serviceCompany2.setUpdatedAt("2016-05-25");
            Contact contact2 = new Contact();
            contact2.setFirstName("Alexandra");
            contact2.setLastName("Lamb");
            contact2.setTel("+91 (990) 522-2052");
            contact2.setSsn("Debbie");
            contact2.setAddress("209 Celeste Court");
            contact2.setCity("Kapowsin");
            contact2.setZip(31776);
            contact2.setCountry("Gabon");
            contact2.setContactEmail("debbielamb@visualix.com");
            contact2.setWebsite("Slater.com");
            contact2.setLatitude(84.120678);
            contact2.setLongitude(63.463442);
        serviceCompany0.setContact(contact2);

        serviceCompanies.add(serviceCompany2);

        ServiceCompany serviceCompany3 = new ServiceCompany();
        serviceCompany3.setName("Godrej");
        serviceCompany3.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.finance_reliance).toString());
        serviceCompany3.setDescription("Comprehensive, Starts form Rs. 3500 p.a");
        serviceCompany3.setId("575acf22971c7d6ccacf371f");
        serviceCompany3.setCreatedAt("2016-05-25");
        serviceCompany3.setUpdatedAt("2016-05-25");
            Contact contact3 = new Contact();
            contact3.setFirstName("Beach");
            contact3.setLastName("Shields");
            contact3.setTel("+91 (950) 402-3848");
            contact3.setSsn("Holt");
            contact3.setAddress("406 Clay Street");
            contact3.setCity("Escondida");
            contact3.setZip(45367);
            contact3.setCountry("Ethiopia");
            contact3.setContactEmail("holtshields@visualix.com");
            contact3.setWebsite("Penelope.com");
            contact3.setLatitude(60.64305);
            contact3.setLongitude(69.668411);
        serviceCompany0.setContact(contact3);

        serviceCompanies.add(serviceCompany3);




        return serviceCompanies;
    }


    public static List<ServiceCompany> createServiceCompanies (){

       if (mockServiceCompanies.size() == 0) {

           ServiceCompany serviceCompany0 = new ServiceCompany();
           serviceCompany0.setName("Hero MotoCorp");
           serviceCompany0.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.hero3).toString());
           serviceCompany0.setDescription("Comprehensive, Starts form Rs. 4000 p.a");
           serviceCompany0.setId("575acf2274550a837e5d9c92");
           serviceCompany0.setCreatedAt("2016-05-25");
           serviceCompany0.setUpdatedAt("2016-05-25");
           Contact contact0 = new Contact();
           contact0.setFirstName("Matthews");
           contact0.setLastName("Barlow");
           contact0.setTel("+91 (900) 445-3807");
           contact0.setSsn("Gale");
           contact0.setAddress("757 Gallatin Place");
           contact0.setCity("Frystown");
           contact0.setZip(86351);
           contact0.setCountry("Faroe Islands");
           contact0.setContactEmail("galebarlow@visualix.com");
           contact0.setWebsite("Silvia.com");
           contact0.setLatitude(66.206727);
           contact0.setLongitude(85.51726);
           serviceCompany0.setContact(contact0);

           mockServiceCompanies.add(serviceCompany0);

           ServiceCompany serviceCompany1 = new ServiceCompany();
           serviceCompany1.setName("Godrej");
           serviceCompany1.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.godrej3).toString());
           serviceCompany1.setDescription("Comprehensive, Starts form Rs. 3800 p.a");
           serviceCompany1.setId("575acf2275674a353e016eb1");
           serviceCompany1.setCreatedAt("2016-05-25");
           serviceCompany1.setUpdatedAt("2016-05-25");
           Contact contact1 = new Contact();
           contact1.setFirstName("Elliott");
           contact1.setLastName("Sloan");
           contact1.setTel("+91 (987) 519-2511");
           contact1.setSsn("Esmeralda");
           contact1.setAddress("689 Surf Avenue");
           contact1.setCity("Fedora");
           contact1.setZip(42554);
           contact1.setCountry("Swaziland");
           contact1.setContactEmail("esmeraldasloan@visualix.com");
           contact1.setWebsite("Miranda.com");
           contact1.setLatitude(74.325343);
           contact1.setLongitude(27.392789);
           serviceCompany0.setContact(contact1);

           mockServiceCompanies.add(serviceCompany1);

//        ServiceCompany serviceCompany2 = new ServiceCompany();
//        serviceCompany2.setName("Hero MotoCorp1");
//        serviceCompany2.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany2.setDescription("Comprehensive, Starts form Rs. 4500 p.a");
//        serviceCompany2.setId("575acf22b15e97e514ee09c9");
//        serviceCompany2.setCreatedAt("2016-05-25");
//        serviceCompany2.setUpdatedAt("2016-05-25");
//            Contact contact2 = new Contact();
//            contact2.setFirstName("Alexandra");
//            contact2.setLastName("Lamb");
//            contact2.setTel("+91 (990) 522-2052");
//            contact2.setSsn("Debbie");
//            contact2.setAddress("209 Celeste Court");
//            contact2.setCity("Kapowsin");
//            contact2.setZip(31776);
//            contact2.setCountry("Gabon");
//            contact2.setContactEmail("debbielamb@visualix.com");
//            contact2.setWebsite("Slater.com");
//            contact2.setLatitude(84.120678);
//            contact2.setLongitude(63.463442);
//        serviceCompany0.setContact(contact2);
//
//        mockServiceCompanies.add(serviceCompany2);
//
//        ServiceCompany serviceCompany3 = new ServiceCompany();
//        serviceCompany3.setName("Godrej");
//        serviceCompany3.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany3.setDescription("Comprehensive, Starts form Rs. 3500 p.a");
//        serviceCompany3.setId("575acf22971c7d6ccacf371f");
//        serviceCompany3.setCreatedAt("2016-05-25");
//        serviceCompany3.setUpdatedAt("2016-05-25");
//            Contact contact3 = new Contact();
//            contact3.setFirstName("Beach");
//            contact3.setLastName("Shields");
//            contact3.setTel("+91 (950) 402-3848");
//            contact3.setSsn("Holt");
//            contact3.setAddress("406 Clay Street");
//            contact3.setCity("Escondida");
//            contact3.setZip(45367);
//            contact3.setCountry("Ethiopia");
//            contact3.setContactEmail("holtshields@visualix.com");
//            contact3.setWebsite("Penelope.com");
//            contact3.setLatitude(60.64305);
//            contact3.setLongitude(69.668411);
//        serviceCompany0.setContact(contact3);
//
//        mockServiceCompanies.add(serviceCompany3);
//
//        ServiceCompany serviceCompany4 = new ServiceCompany();
//        serviceCompany4.setName("Hero MotoCorp2");
//        serviceCompany4.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany4.setDescription("Comprehensive, Starts form Rs. 3000 p.a");
//        serviceCompany4.setId("575acf22c0f1ccff86fd9c74");
//        serviceCompany4.setCreatedAt("2016-05-25");
//        serviceCompany4.setUpdatedAt("2016-05-25");
//            Contact contact4 = new Contact();
//            contact4.setFirstName("Roslyn");
//            contact4.setLastName("Jefferson");
//            contact4.setTel("+91 (968) 506-2024");
//            contact4.setSsn("Moore");
//            contact4.setAddress("374 Verona Place");
//            contact4.setCity("Bridgetown");
//            contact4.setZip(39468);
//            contact4.setCountry("Reunion");
//            contact4.setContactEmail("moorejefferson@visualix.com");
//            contact4.setWebsite("Keller.com");
//            contact4.setLatitude(62.11774);
//            contact4.setLongitude(72.249163);
//        serviceCompany0.setContact(contact4);
//
//        mockServiceCompanies.add(serviceCompany4);
//
//        ServiceCompany serviceCompany5 = new ServiceCompany();
//        serviceCompany5.setName("Godrej");
//        serviceCompany5.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany5.setDescription("Comprehensive, Starts form Rs. 5000 p.a");
//        serviceCompany5.setId("575acf22b5e0387fba50fa1e");
//        serviceCompany5.setCreatedAt("2016-05-25");
//        serviceCompany5.setUpdatedAt("2016-05-25");
//            Contact contact5 = new Contact();
//            contact5.setFirstName("Regina");
//            contact5.setLastName("Gaines");
//            contact5.setTel("+91 (927) 498-3942");
//            contact5.setSsn("Debra");
//            contact5.setAddress("260 Aviation Road");
//            contact5.setCity("Freelandville");
//            contact5.setZip(10893);
//            contact5.setCountry("Bosnia and Herzegovina");
//            contact5.setContactEmail("debragaines@visualix.com");
//            contact5.setWebsite("Rhoda.com");
//            contact5.setLatitude(20.673356);
//            contact5.setLongitude(33.945107);
//        serviceCompany0.setContact(contact5);
//
//        mockServiceCompanies.add(serviceCompany5);
//
//        ServiceCompany serviceCompany6 = new ServiceCompany();
//        serviceCompany6.setName("Hero MotoCorp3");
//        serviceCompany6.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany6.setDescription("Comprehensive, Starts form Rs. 4300 p.a");
//        serviceCompany6.setId("575acf228194ac5d99933b3d");
//        serviceCompany6.setCreatedAt("2016-05-25");
//        serviceCompany6.setUpdatedAt("2016-05-25");
//            Contact contact6 = new Contact();
//            contact6.setFirstName("Lowe");
//            contact6.setLastName("Goff");
//            contact6.setTel("+91 (992) 438-2760");
//            contact6.setSsn("Tina");
//            contact6.setAddress("441 Kansas Place");
//            contact6.setCity("Blackgum");
//            contact6.setZip(58621);
//            contact6.setCountry("Canada");
//            contact6.setContactEmail("tinagoff@visualix.com");
//            contact6.setWebsite("Guy.com");
//            contact6.setLatitude(32.919691);
//            contact6.setLongitude(44.907874);
//        serviceCompany0.setContact(contact6);
//
//        mockServiceCompanies.add(serviceCompany6);
       }

        return mockServiceCompanies;
    }
    public static List<ServiceCompany> createInsuranceServiceCompanies (){

        List<ServiceCompany> serviceCompanies = new ArrayList<>();

        ServiceCompany serviceCompany0 = new ServiceCompany();
        serviceCompany0.setName("Hero FinCorp");
        serviceCompany0.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.hero3).toString());
        serviceCompany0.setDescription("Comprehensive, Starts form Rs. 4000 p.a");
        serviceCompany0.setId("575acf2274550a837e5d9c92");
        serviceCompany0.setCreatedAt("2016-05-25");
        serviceCompany0.setUpdatedAt("2016-05-25");
        Contact contact0 = new Contact();
        contact0.setFirstName("Matthews");
        contact0.setLastName("Barlow");
        contact0.setTel("+91 (900) 445-3807");
        contact0.setSsn("Gale");
        contact0.setAddress("757 Gallatin Place");
        contact0.setCity("Frystown");
        contact0.setZip(86351);
        contact0.setCountry("Faroe Islands");
        contact0.setContactEmail("galebarlow@visualix.com");
        contact0.setWebsite("Silvia.com");
        contact0.setLatitude(66.206727);
        contact0.setLongitude(85.51726);
        serviceCompany0.setContact(contact0);

        serviceCompanies.add(serviceCompany0);

//        ServiceCompany serviceCompany1 = new ServiceCompany();
//        serviceCompany1.setName("IFFCO TOKIO");
//        serviceCompany1.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.walletbrand_iffco_tokio).toString());
//        serviceCompany1.setDescription("Comprehensive, Starts form Rs. 3800 p.a");
//        serviceCompany1.setId("575acf2275674a353e016eb1");
//        serviceCompany1.setCreatedAt("2016-05-25");
//        serviceCompany1.setUpdatedAt("2016-05-25");
//        Contact contact1 = new Contact();
//        contact1.setFirstName("Elliott");
//        contact1.setLastName("Sloan");
//        contact1.setTel("+91 (987) 519-2511");
//        contact1.setSsn("Esmeralda");
//        contact1.setAddress("689 Surf Avenue");
//        contact1.setCity("Fedora");
//        contact1.setZip(42554);
//        contact1.setCountry("Swaziland");
//        contact1.setContactEmail("esmeraldasloan@visualix.com");
//        contact1.setWebsite("Miranda.com");
//        contact1.setLatitude(74.325343);
//        contact1.setLongitude(27.392789);
//        serviceCompany1.setContact(contact1);
//
//        serviceCompanies.add(serviceCompany1);
//
//        ServiceCompany serviceCompany2 = new ServiceCompany();
//        serviceCompany2.setName("Reliance General");
//        serviceCompany2.setImageUrl(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.walletbrand_reliance).toString());
//        serviceCompany2.setDescription("Comprehensive, Starts form Rs. 4000 p.a");
//        serviceCompany2.setId("575acf2275674a353e016eb1");
//        serviceCompany2.setCreatedAt("2016-05-25");
//        serviceCompany2.setUpdatedAt("2016-05-25");
//        Contact contact2 = new Contact();
//        contact2.setFirstName("Elliott");
//        contact2.setLastName("Sloan");
//        contact2.setTel("+91 (987) 519-2511");
//        contact2.setSsn("Esmeralda");
//        contact2.setAddress("689 Surf Avenue");
//        contact2.setCity("Fedora");
//        contact2.setZip(42554);
//        contact2.setCountry("Swaziland");
//        contact2.setContactEmail("esmeraldasloan@visualix.com");
//        contact2.setWebsite("Miranda.com");
//        contact2.setLatitude(74.325343);
//        contact2.setLongitude(27.392789);
//        serviceCompany2.setContact(contact2);
//
//        serviceCompanies.add(serviceCompany2);

//        ServiceCompany serviceCompany2 = new ServiceCompany();
//        serviceCompany2.setName("Hero MotoCorp1");
//        serviceCompany2.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany2.setDescription("Comprehensive, Starts form Rs. 4500 p.a");
//        serviceCompany2.setId("575acf22b15e97e514ee09c9");
//        serviceCompany2.setCreatedAt("2016-05-25");
//        serviceCompany2.setUpdatedAt("2016-05-25");
//            Contact contact2 = new Contact();
//            contact2.setFirstName("Alexandra");
//            contact2.setLastName("Lamb");
//            contact2.setTel("+91 (990) 522-2052");
//            contact2.setSsn("Debbie");
//            contact2.setAddress("209 Celeste Court");
//            contact2.setCity("Kapowsin");
//            contact2.setZip(31776);
//            contact2.setCountry("Gabon");
//            contact2.setContactEmail("debbielamb@visualix.com");
//            contact2.setWebsite("Slater.com");
//            contact2.setLatitude(84.120678);
//            contact2.setLongitude(63.463442);
//        serviceCompany0.setContact(contact2);
//
//        serviceCompanies.add(serviceCompany2);
//
//        ServiceCompany serviceCompany3 = new ServiceCompany();
//        serviceCompany3.setName("Godrej");
//        serviceCompany3.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany3.setDescription("Comprehensive, Starts form Rs. 3500 p.a");
//        serviceCompany3.setId("575acf22971c7d6ccacf371f");
//        serviceCompany3.setCreatedAt("2016-05-25");
//        serviceCompany3.setUpdatedAt("2016-05-25");
//            Contact contact3 = new Contact();
//            contact3.setFirstName("Beach");
//            contact3.setLastName("Shields");
//            contact3.setTel("+91 (950) 402-3848");
//            contact3.setSsn("Holt");
//            contact3.setAddress("406 Clay Street");
//            contact3.setCity("Escondida");
//            contact3.setZip(45367);
//            contact3.setCountry("Ethiopia");
//            contact3.setContactEmail("holtshields@visualix.com");
//            contact3.setWebsite("Penelope.com");
//            contact3.setLatitude(60.64305);
//            contact3.setLongitude(69.668411);
//        serviceCompany0.setContact(contact3);
//
//        serviceCompanies.add(serviceCompany3);
//
//        ServiceCompany serviceCompany4 = new ServiceCompany();
//        serviceCompany4.setName("Hero MotoCorp2");
//        serviceCompany4.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany4.setDescription("Comprehensive, Starts form Rs. 3000 p.a");
//        serviceCompany4.setId("575acf22c0f1ccff86fd9c74");
//        serviceCompany4.setCreatedAt("2016-05-25");
//        serviceCompany4.setUpdatedAt("2016-05-25");
//            Contact contact4 = new Contact();
//            contact4.setFirstName("Roslyn");
//            contact4.setLastName("Jefferson");
//            contact4.setTel("+91 (968) 506-2024");
//            contact4.setSsn("Moore");
//            contact4.setAddress("374 Verona Place");
//            contact4.setCity("Bridgetown");
//            contact4.setZip(39468);
//            contact4.setCountry("Reunion");
//            contact4.setContactEmail("moorejefferson@visualix.com");
//            contact4.setWebsite("Keller.com");
//            contact4.setLatitude(62.11774);
//            contact4.setLongitude(72.249163);
//        serviceCompany0.setContact(contact4);
//
//        serviceCompanies.add(serviceCompany4);
//
//        ServiceCompany serviceCompany5 = new ServiceCompany();
//        serviceCompany5.setName("Godrej");
//        serviceCompany5.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany5.setDescription("Comprehensive, Starts form Rs. 5000 p.a");
//        serviceCompany5.setId("575acf22b5e0387fba50fa1e");
//        serviceCompany5.setCreatedAt("2016-05-25");
//        serviceCompany5.setUpdatedAt("2016-05-25");
//            Contact contact5 = new Contact();
//            contact5.setFirstName("Regina");
//            contact5.setLastName("Gaines");
//            contact5.setTel("+91 (927) 498-3942");
//            contact5.setSsn("Debra");
//            contact5.setAddress("260 Aviation Road");
//            contact5.setCity("Freelandville");
//            contact5.setZip(10893);
//            contact5.setCountry("Bosnia and Herzegovina");
//            contact5.setContactEmail("debragaines@visualix.com");
//            contact5.setWebsite("Rhoda.com");
//            contact5.setLatitude(20.673356);
//            contact5.setLongitude(33.945107);
//        serviceCompany0.setContact(contact5);
//
//        serviceCompanies.add(serviceCompany5);
//
//        ServiceCompany serviceCompany6 = new ServiceCompany();
//        serviceCompany6.setName("Hero MotoCorp3");
//        serviceCompany6.setImageUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
//        serviceCompany6.setDescription("Comprehensive, Starts form Rs. 4300 p.a");
//        serviceCompany6.setId("575acf228194ac5d99933b3d");
//        serviceCompany6.setCreatedAt("2016-05-25");
//        serviceCompany6.setUpdatedAt("2016-05-25");
//            Contact contact6 = new Contact();
//            contact6.setFirstName("Lowe");
//            contact6.setLastName("Goff");
//            contact6.setTel("+91 (992) 438-2760");
//            contact6.setSsn("Tina");
//            contact6.setAddress("441 Kansas Place");
//            contact6.setCity("Blackgum");
//            contact6.setZip(58621);
//            contact6.setCountry("Canada");
//            contact6.setContactEmail("tinagoff@visualix.com");
//            contact6.setWebsite("Guy.com");
//            contact6.setLatitude(32.919691);
//            contact6.setLongitude(44.907874);
//        serviceCompany0.setContact(contact6);
//
//        serviceCompanies.add(serviceCompany6);

        return serviceCompanies;
    }

    public static List<Message> createReminderMessages (){

       if (mockReminderMessages.size() == 0) {
           Message message0 = new Message();
           message0.setId("2");
           message0.setCreatedAt("02-01-2016    11:20 AM");
           message0.setUpdatedAt("02-01-2016    11:20 AM");

           RoleInfo to = new RoleInfo();
           to.setId("c1");
           to.setRole("consumer");
           message0.setTo(to);
           RoleInfo from = new RoleInfo();
           from.setId("r1");
           from.setRole("reminder");
           message0.setFrom(from);
           String reminderContent0 = "{'name' : 'Hero Motocorp', 'imageThumb': '" + Uri.parse("android.resource://com.warrantix.main/" + R.drawable.hero3).toString()
                   + "', 'sub': 'lorem Ipsum is simply dummy text of the printing', 'description':'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a aliquet lorem, vitae laoreet odio. Nullam eu neque et nunc cursus commodo.Vivamus at magna eleifend, luctus augue at, ullamcorper augue.'}";
           message0.setContent(reminderContent0);

           mockReminderMessages.add(message0);

           Message message1 = new Message();
           message1.setId("3");
           message1.setCreatedAt("16-03-2016    11:20 AM");
           message1.setUpdatedAt("16-03-2016    11:20 AM");
           message0.setTo(to);
           RoleInfo from1 = new RoleInfo();
           from1.setId("r2");
           from1.setRole("reminder");
           message1.setFrom(from1);
           String reminderContent1 = "{'name' : 'Godrej', 'imageThumb': '" + Uri.parse("android.resource://com.warrantix.main/" + R.drawable.godrej3).toString()
                   + "', 'sub': 'lorem Ipsum is simply dummy text of the printing', 'description':'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras a aliquet lorem, vitae laoreet odio. Nullam eu neque et nunc cursus commodo. Vivamus at magna eleifend, luctus augue at, ullamcorper augue.'}";
           message1.setContent(reminderContent1);

           mockReminderMessages.add(message1);
       }

        return mockReminderMessages;
    }

    public static List<Message> createRevealMessages (){

        if (mockRevealMessages.size() == 0) {

            Message message0 = new Message();
            message0.setId("2");
            message0.setCreatedAt("2016-06-03");
            message0.setUpdatedAt("2016-06-03");
            RoleInfo to = new RoleInfo();
            to.setId("c1");
            to.setRole("consumer");
            message0.setTo(to);
            RoleInfo from = new RoleInfo();
            from.setId("reveal0");
            from.setRole("reveal");
            message0.setFrom(from);
            String Content0 = "{'type' : 'reveal', 'productID' : '575a963055b8439f41aac0ff','customerID' : 'c0','brandID' : 'Hero'}";
            message0.setContent(Content0);

            mockRevealMessages.add(message0);

            Message message1 = new Message();
            message1.setId("3");
            message1.setCreatedAt("2016-06-03");
            message1.setUpdatedAt("2016-06-03");
            message1.setTo(to);
            RoleInfo from1 = new RoleInfo();
            from1.setId("reveal1");
            from1.setRole("reveal");
            message1.setFrom(from1);
            String Content1 = "{'type' : 'reveal', 'productID' : '575a963098d67ff6c6e38cc9','customerID' : 'c1','brandID' : 'Hero'}";
            message1.setContent(Content1);

            mockRevealMessages.add(message1);

            Message message2 = new Message();
            message2.setId("1");
            message2.setCreatedAt("2016-06-03");
            message2.setUpdatedAt("2016-06-03");
            message2.setTo(to);
            RoleInfo from2 = new RoleInfo();
            from2.setId("reveal2");
            from2.setRole("reveal");
            message2.setFrom(from1);
            String Content2 = "{'type' : 'reveal', 'productID' : '575a96301d43624062a44c2c','customerID' : 'c2','brandID' : 'Hero'}";
            message2.setContent(Content2);

            mockRevealMessages.add(message2);

            Message message3 = new Message();
            message3.setId("3");
            message3.setCreatedAt("2016-06-03");
            message3.setUpdatedAt("2016-06-03");
            message3.setTo(to);
            RoleInfo from3 = new RoleInfo();
            from3.setId("reveal3");
            from3.setRole("reveal");
            message3.setFrom(from3);
            String Content3 = "{'type' : 'reveal', 'productID' : '575a963012d141a611c9a358','customerID' : 'c3','brandID' : 'Hero'}";
            message3.setContent(Content3);

            mockRevealMessages.add(message3);
        }

        return mockRevealMessages;
    }


    public static List<Message> createReferMessages (){

       if (mockReferMessages.size() == 0) {

           Message message0 = new Message();
           message0.setId("2");
           message0.setCreatedAt("2016-06-03");
           message0.setUpdatedAt("2016-06-03");
           RoleInfo to = new RoleInfo();
           to.setId("c1");
           to.setRole("consumer");
           message0.setTo(to);
           RoleInfo from = new RoleInfo();
           from.setId("refer0");
           from.setRole("refer");
           message0.setFrom(from);
           String Content0 = "{'type' : 'refer', 'productID' : '575a963055b8439f41aac0ff','customerID' : 'c0','brandID' : 'Hero', 'content':'Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.'}";
           message0.setContent(Content0);

           mockReferMessages.add(message0);

           Message message1 = new Message();
           message1.setId("3");
           message1.setCreatedAt("2016-06-03");
           message1.setUpdatedAt("2016-06-03");
           message1.setTo(to);
           RoleInfo from1 = new RoleInfo();
           from1.setId("refer1");
           from1.setRole("refer");
           message1.setFrom(from1);
           String Content1 = "{'type' : 'refer', 'productID' : '575a963098d67ff6c6e38cc9','customerID' : 'c1','brandID' : 'Hero', 'content':'Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.'}";
           message1.setContent(Content1);

           mockReferMessages.add(message1);

           Message message2 = new Message();
           message2.setId("1");
           message2.setCreatedAt("2016-06-03");
           message2.setUpdatedAt("2016-06-03");
           message2.setTo(to);
           RoleInfo from2 = new RoleInfo();
           from2.setId("refer2");
           from2.setRole("refer");
           message2.setFrom(from1);
           String Content2 = "{'type' : 'refer', 'productID' : '575a96301d43624062a44c2c','customerID' : 'c0','brandID' : 'Hero', 'content':'Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.'}";
           message2.setContent(Content2);

           mockReferMessages.add(message1);

           Message message3 = new Message();
           message3.setId("3");
           message3.setCreatedAt("2016-06-03");
           message3.setUpdatedAt("2016-06-03");
           message3.setTo(to);
           RoleInfo from3 = new RoleInfo();
           from3.setId("refer3");
           from3.setRole("refer");
           message3.setFrom(from3);
           String Content3 = "{'type' : 'refer', 'productID' : '575a963012d141a611c9a358','customerID' : 'c1','brandID' : 'Hero','content':'Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.'}";
           message3.setContent(Content3);

           mockReferMessages.add(message3);
       }

        return mockReferMessages;
    }

    public static List<Message> createChatMessages (boolean recent){

       List<Message> messages = new ArrayList<>();

        // recent messages
        Message message0 = new Message();
        message0.setId("0");
        message0.setCreatedAt("2016-06-08 02:12:30");
        message0.setUpdatedAt("2016-06-08 02:12:30");
        RoleInfo to = new RoleInfo();
        to.setId("c0");
        to.setRole("consumer");
        message0.setTo(to);
        RoleInfo from = new RoleInfo();
        from.setId("c3");
        from.setRole("consumer");
        message0.setFrom(from);
        String Content0 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
        message0.setContent(Content0);

        messages.add(message0);

        Message message1 = new Message();
        message1.setId("1");
        message1.setCreatedAt("2016-06-07 02:12:30");
        message1.setUpdatedAt("2016-06-07 02:12:30");
        message1.setTo(to);
        RoleInfo from1 = new RoleInfo();
        from1.setId("c1");
        from1.setRole("consumer");
        message1.setFrom(from1);
        String Content1 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
        message1.setContent(Content1);

        messages.add(message1);

        Message message2 = new Message();
        message2.setId("2");
        message2.setCreatedAt("2016-06-06 02:12:30");
        message2.setUpdatedAt("2016-06-06 02:12:30");
        message2.setTo(to);
        RoleInfo from2 = new RoleInfo();
        from2.setId("c3");
        from2.setRole("consumer");
        message2.setFrom(from2);
        String Content2 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
        message2.setContent(Content2);

        messages.add(message2);

        //next recent Chat messages- please use to assume when next recent chat message is recieved.
//
//        Message message3 = new Message();
//        message3.setId("3");
//        message3.setCreatedAt("2016-06-05");
//        message3.setUpdatedAt("2016-06-05");
//        message3.setTo(to);
//        message3.setFrom(from1);
//        String Content3 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
//        message3.setContent(Content3);
//
//        messages.add(message3);
//
//        mockChatMessages.add(message1);
//
//        Message message4 = new Message();
//        message4.setId("4");
//        message4.setCreatedAt("2016-06-04");
//        message4.setUpdatedAt("2016-06-04");
//        message4.setTo(to);
//        RoleInfo from4 = new RoleInfo();
//        from4.setId("c2");
//        from4.setRole("consumer");
//        message4.setFrom(from4);
//        String Content4 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
//        message4.setContent(Content4);
//
//        messages.add(message4);
//
//        Message message5 = new Message();
//        message5.setId("5");
//        message5.setCreatedAt("2016-06-03");
//        message5.setUpdatedAt("2016-06-03");
//        message5.setTo(to);
//        RoleInfo from5 = new RoleInfo();
//        from5.setId("c2");
//        from5.setRole("consumer");
//        message5.setFrom(from5);
//        String Content5 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
//        message5.setContent(Content5);
//
//        messages.add(message5);


        if (recent != true) {
            // old messages
            Message message6 = new Message();
            message6.setId("6");
            message6.setCreatedAt("2016-06-02 02:12:30");
            message6.setUpdatedAt("2016-06-02 02:12:30");
            message6.setTo(to);
            RoleInfo from6 = new RoleInfo();
            from6.setId("c3");
            from6.setRole("consumer");
            message6.setFrom(from6);
            String Content6 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
            message6.setContent(Content6);

            messages.add(message6);

            Message message7 = new Message();
            message7.setId("7");
            message7.setCreatedAt("2016-06-01 02:12:30");
            message7.setUpdatedAt("2016-06-01 02:12:30");
            message7.setTo(to);
            RoleInfo from7 = new RoleInfo();
            from7.setId("c1");
            from7.setRole("consumer");
            message7.setFrom(from7);
            String Content7 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
            message7.setContent(Content7);

            messages.add(message7);

            Message message8 = new Message();
            message8.setId("8");
            message8.setCreatedAt("2016-05-30 02:12:30");
            message8.setUpdatedAt("2016-05-30 02:12:30");
            message8.setTo(to);
            RoleInfo from8 = new RoleInfo();
            from8.setId("c2");
            from8.setRole("consumer");
            message8.setFrom(from8);
            String Content8 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
            message8.setContent(Content8);

            messages.add(message8);

            Message message9 = new Message();
            message9.setId("9");
            message9.setCreatedAt("2016-05-29 02:12:30");
            message9.setUpdatedAt("2016-05-29 02:12:30");
            message9.setTo(to);
            RoleInfo from9 = new RoleInfo();
            from9.setId("c3");
            from9.setRole("consumer");
            message9.setFrom(from9);
            String Content9 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
            message9.setContent(Content9);

            messages.add(message9);

            Message message10 = new Message();
            message10.setId("10");
            message10.setCreatedAt("2016-05-28 02:12:30");
            message10.setUpdatedAt("2016-06-28 02:12:30");
            message10.setTo(to);
            RoleInfo from10 = new RoleInfo();
            from10.setId("c3");
            from10.setRole("consumer");
            message10.setFrom(from10);
            String Content10 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
            message10.setContent(Content10);

            messages.add(message7);

            Message message11 = new Message();
            message11.setId("11");
            message11.setCreatedAt("2016-05-27 02:12:30");
            message11.setUpdatedAt("2016-05-27 02:12:30");
            message11.setTo(to);
            RoleInfo from11 = new RoleInfo();
            from11.setId("c2");
            from11.setRole("consumer");
            message11.setFrom(from11);
            String Content11 = "Lorem occaecat minim ipsum aute id deserunt Lorem aliqua quis consectetur. Eu deserunt fugiat magna quis fugiat.";
            message11.setContent(Content11);

            messages.add(message11);
        }

        return messages;
    }


    public static List<Message> getChatMessagesByCustomerId(String customerID){

        List<Message> messages = new ArrayList<>();
        List<Message> subMessages = new ArrayList<>();
        messages = createChatMessages(false);

        if (messages != null) {
            for (int i = 0; i < messages.size(); i++){
                Message message = messages.get(i);
                if (message.getFrom().getId().equals(customerID)){
                    subMessages.add(message);
                }
            }
        }
        return subMessages;

    }



    public static List<Dealer> createDealer (){

       if (mockDealers.size() == 0) {

           Dealer dealer0 = new Dealer();
           dealer0.setId("dealer0");
           dealer0.setName("CF Moto");
           dealer0.setDescription("Stadium Corssroads, navrangpura");
           dealer0.setBrandID("brandId");
           dealer0.setCreatedAt("2016-06-03");
           dealer0.setUpdatedAt("2016-06-03");
           dealer0.setRate(4);
           dealer0.setImagelUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
           Contact contact0 = new Contact();
           contact0.setFirstName("Matthews");
           contact0.setLastName("Barlow");
           contact0.setTel("+91 (900) 445-3807");
           contact0.setSsn("Gale");
           contact0.setAddress("Shyamal Corssroads, Satelite");
           contact0.setCity("Frystown");
           contact0.setZip(86351);
           contact0.setCountry("Faroe Islands");
           contact0.setContactEmail("galebarlow@visualix.com");
           contact0.setWebsite("Silvia.com");
           contact0.setLatitude(23.206727);
           contact0.setLongitude(79.51726);
           dealer0.setContact(contact0);
           Deal deal0 = new Deal();
           deal0.setProductID("575a96305764b195b7c0f999");
           deal0.setBrandID("brandId");
           deal0.setPrice(53000);
           deal0.setShortDescription("15% off");
           List<Deal> deals0 = new ArrayList<>();
           deals0.add(deal0);
           dealer0.setDeals(deals0);

           mockDealers.add(dealer0);

           Dealer dealer1 = new Dealer();
           dealer1.setId("dealer1");
           dealer1.setName("Hero Motocycles");
           dealer1.setDescription("Shamal Corssroads, Satellite");
           dealer1.setBrandID("brandId");
           dealer1.setCreatedAt("2016-06-03");
           dealer1.setUpdatedAt("2016-06-03");
           dealer1.setRate(4);
           dealer1.setImagelUrl("https://www.heromotocorp.com/en-in/uploads/bike/bike_color_pic/20150302121004-color-main-282.png");
           Contact contact1 = new Contact();
           contact1.setFirstName("Elliott");
           contact1.setLastName("Sloan");
           contact1.setTel("+91 (987) 519-2511");
           contact1.setSsn("Esmeralda");
           contact1.setAddress("Stadium Corssroads, Navrangpura");
           contact1.setCity("Fedora");
           contact1.setZip(42554);
           contact1.setCountry("Swaziland");
           contact1.setContactEmail("esmeraldasloan@visualix.com");
           contact1.setWebsite("Miranda.com");
           contact1.setLatitude(40.118421);
           contact1.setLongitude(124.391706);
           dealer1.setContact(contact1);
           Deal deal1 = new Deal();
           deal1.setProductID("575a9630d5725ffc91c74eab");
           deal1.setBrandID("brandId");
           deal1.setPrice(60000);
           deal1.setShortDescription("20% off");
           List<Deal> deals1 = new ArrayList<>();
           deals1.add(deal1);
           dealer1.setDeals(deals1);
           mockDealers.add(dealer1);
       }

        return mockDealers;
    }

    public static List<Order> createOrders(){

        if (mockOrders.size() == 0) {

            Order order0 = new Order();
            order0.setId("order0");
            order0.setProductID("575a963055b8439f41aac0ff");
            order0.setCartID("cart0");
            Contact contact0 = new Contact();
            contact0.setFirstName("Matthews");
            contact0.setLastName("Barlow");
            contact0.setTel("+91 (900) 445-3807");
            contact0.setSsn("Gale");
            contact0.setAddress("757 Gallatin Place");
            contact0.setCity("Frystown");
            contact0.setZip(86351);
            contact0.setCountry("Faroe Islands");
            contact0.setContactEmail("galebarlow@visualix.com");
            contact0.setWebsite("Silvia.com");
            contact0.setLatitude(66.206727);
            contact0.setLongitude(85.51726);
            order0.setContact(contact0);
            order0.setCurrency("INR");
            order0.setCreatedAt("2016-06-03");
            order0.setCustomerID("c1");
            order0.setDeliveryCharges(50);
            order0.setDeliveryDate("2016-06-03");
            order0.setQuantity(1);
            order0.setStatus("On the Way");
            order0.setTotalAmount(100);
            order0.setUnitPrice(30);
            order0.setUpdatedAt("2016-06-03");

            mockOrders.add(order0);

            Order order1 = new Order();
            order1.setId("order1");
            order1.setProductID("575a96305764b195b7c0f999");
            order1.setCartID("cart1");
            Contact contact1 = new Contact();
            contact1.setFirstName("Elliott");
            contact1.setLastName("Sloan");
            contact1.setTel("+91 (987) 519-2511");
            contact1.setSsn("Esmeralda");
            contact1.setAddress("689 Surf Avenue");
            contact1.setCity("Fedora");
            contact1.setZip(42554);
            contact1.setCountry("Swaziland");
            contact1.setContactEmail("esmeraldasloan@visualix.com");
            contact1.setWebsite("Miranda.com");
            contact1.setLatitude(74.325343);
            contact1.setLongitude(27.392789);
            order1.setContact(contact1);
            order1.setCurrency("INR");
            order1.setCreatedAt("2016-06-03");
            order1.setCustomerID("c3");
            order1.setDeliveryCharges(40);
            order1.setDeliveryDate("2016-06-03");
            order1.setQuantity(2);
            order1.setStatus("Delivered Wednesday");
            order1.setTotalAmount(100);
            order1.setUnitPrice(40);
            order1.setUpdatedAt("2016-06-03");

            mockOrders.add(order1);

            Order order2 = new Order();
            order2.setId("order2");
            order2.setProductID("575a9630d5725ffc91c74eab");
            order2.setCartID("cart1");
            order2.setContact(contact1);
            order2.setCurrency("INR");
            order2.setCreatedAt("2016-06-03");
            order2.setCustomerID("c2");
            order2.setDeliveryCharges(40);
            order2.setDeliveryDate("2016-06-03");
            order2.setQuantity(2);
            order2.setStatus("On the way");
            order2.setTotalAmount(150);
            order2.setUnitPrice(80);
            order2.setUpdatedAt("2016-06-03");

            mockOrders.add(order2);

            Order order3 = new Order();
            order3.setId("order3");
            order3.setProductID("575a963055b8439f41aac0ff");
            order3.setCartID("cart3");
            Contact contact3 = new Contact();
            contact3.setFirstName("Elliott");
            contact3.setLastName("Sloan");
            contact3.setTel("+91 (987) 519-2511");
            contact3.setSsn("Esmeralda");
            contact3.setAddress("689 Surf Avenue");
            contact3.setCity("Fedora");
            contact3.setZip(42554);
            contact3.setCountry("Swaziland");
            contact3.setContactEmail("esmeraldasloan@visualix.com");
            contact3.setWebsite("Miranda.com");
            contact3.setLatitude(74.325343);
            contact3.setLongitude(27.392789);
            order3.setContact(contact3);
            order3.setCurrency("INR");
            order3.setCreatedAt("2016-06-03");
            order3.setCustomerID("c1");
            order3.setDeliveryCharges(40);
            order3.setDeliveryDate("2016-06-03");
            order3.setQuantity(2);
            order3.setStatus("Delivered Wednesday");
            order3.setTotalAmount(100);
            order3.setUnitPrice(40);
            order3.setUpdatedAt("2016-06-03");

            mockOrders.add(order3);
        }
        return mockOrders;
    }

    public static List<Order> getOrders(String cartID){

        List<Order> orders = createOrders();

        List<Order> subOrders = new ArrayList<>();

        for (int i = 0; i < orders.size(); i++){
            Order order = orders.get(i);
            if (order.getCartID().equals(cartID)){
                subOrders.add(order);
            }
        }

        return subOrders;
    }

    public static void deleteOrder(String orderID) {
        if (mockOrders == null)
            return;

        for (int i = 0; i < mockOrders.size(); i++) {
            Order order = mockOrders.get(i);
            if (order.getId().equalsIgnoreCase(orderID))
                mockOrders.remove(order);
        }
    }

    public static String addOrder(AddProductToCartRequest addProductToCartRequest){

        String message = null;

        if (mockOrders.size() == 0)
            createOrders();

        Boolean isProduct = false;
        for (int i = 0; i < mockOrders.size(); i++) {
            Order order = mockOrders.get(i);
            if (order.getProductID().equalsIgnoreCase(addProductToCartRequest.getProductID())){
                isProduct = true;
                break;
            }
        }

        if (isProduct == false){

            Order newOrder = new Order();
            int item = mockOrders.size()+1;
            newOrder.setId("order" + item);
            newOrder.setProductID(addProductToCartRequest.getProductID());
            newOrder.setCartID(addProductToCartRequest.getCartID());
            Contact contact1 = new Contact();
            contact1.setFirstName("Elliott");
            contact1.setLastName("Sloan");
            contact1.setTel("+91 (987) 519-2511");
            contact1.setSsn("Esmeralda");
            contact1.setAddress("689 Surf Avenue");
            contact1.setCity("Fedora");
            contact1.setZip(42554);
            contact1.setCountry("Swaziland");
            contact1.setContactEmail("esmeraldasloan@visualix.com");
            contact1.setWebsite("Miranda.com");
            contact1.setLatitude(74.325343);
            contact1.setLongitude(27.392789);
            newOrder.setContact(contact1);
            newOrder.setCurrency("INR");
            newOrder.setCreatedAt("2016-06-03");
            newOrder.setCustomerID(addProductToCartRequest.getCustomerID());
            newOrder.setDeliveryCharges(40);
            newOrder.setDeliveryDate("2016-06-03");
            newOrder.setQuantity(addProductToCartRequest.getQuantity());
            newOrder.setStatus("On the way");
            newOrder.setTotalAmount(150);
            newOrder.setUnitPrice(80);
            newOrder.setUpdatedAt("2016-06-03");

            mockOrders.add(newOrder);

            message = "Added!";
        } else {
            message = "You already ordered this!";
        }


        return message;
    }


    public static RelatedProduct getRelatedProduct(String _Id){

        List<RelatedProduct> relatedProducts = new ArrayList<>();
        RelatedProduct selRelatedProduct = new RelatedProduct();
        relatedProducts = createRelatedProducts();
        for (int i = 0; i < relatedProducts.size()-1; i++){
            RelatedProduct relatedProduct = relatedProducts.get(i);
            if (relatedProduct.getId().equals(_Id)){
                selRelatedProduct = relatedProduct;
            }
        }
        return  selRelatedProduct;
    }



    public static List<UsedProduct> createUsedProducts(){

        if (mockUsedProducts.size() == 0 ) {

            UsedProduct usedProduct0 = new UsedProduct();
            usedProduct0.setId("575aa374d0a3dddda3ccb2d1");
            MsrpCurrency msrpCurrency = new MsrpCurrency();
            msrpCurrency.setMsrpCurrency("INR");
            usedProduct0.setMsrpCurrency(msrpCurrency);
            usedProduct0.setMsrp(100);
            usedProduct0.setBrandID("Hero");
            usedProduct0.setCreatedAt("2016/06/10");
            usedProduct0.setUpdatedAt("2016/06/10");
            usedProduct0.setCustomerID("575aa374d06f7df6578904d6");
            usedProduct0.setProductID("575a963055b8439f41aac0ff");
            usedProduct0.setWarrantyID("2016/06/10");

            mockUsedProducts.add(usedProduct0);

            UsedProduct usedProduct1 = new UsedProduct();
            usedProduct1.setId("575aa374dbdbf1be41895293");
            usedProduct1.setMsrpCurrency(msrpCurrency);
            usedProduct1.setMsrp(345);
            usedProduct1.setBrandID("Hero");
            usedProduct1.setCreatedAt("2016/06/10");
            usedProduct1.setUpdatedAt("2016/06/10");
            usedProduct1.setCustomerID("575aa3746a675ace7bc03e4c");
            usedProduct1.setProductID("575a96305764b195b7c0f784");
            usedProduct1.setWarrantyID("2016/06/10");

            mockUsedProducts.add(usedProduct1);

            UsedProduct usedProduct2 = new UsedProduct();
            usedProduct2.setId("575aa37428dd5e00c221e7a4");
            usedProduct2.setMsrpCurrency(msrpCurrency);
            usedProduct2.setMsrp(634);
            usedProduct2.setBrandID("Hero");
            usedProduct2.setCreatedAt("2016/06/10");
            usedProduct2.setUpdatedAt("2016/06/10");
            usedProduct2.setCustomerID("575aa37475a3cfdef25965b4");
            usedProduct2.setProductID("575a9630d5725ffc91c74eab");
            usedProduct2.setWarrantyID("2016/06/10");

            mockUsedProducts.add(usedProduct2);

            UsedProduct usedProduct3 = new UsedProduct();
            usedProduct3.setId("575aa374c4fd4f639ed44b2f");
            usedProduct3.setMsrpCurrency(msrpCurrency);
            usedProduct3.setMsrp(234);
            usedProduct3.setBrandID("Hero");
            usedProduct3.setCreatedAt("2016/06/10");
            usedProduct3.setUpdatedAt("2016/06/10");
            usedProduct3.setCustomerID("575aa3747b1c545c44b3da9f");
            usedProduct3.setProductID("575a9630ac9474317edd8aab");
            usedProduct3.setWarrantyID("2016/06/10");

            mockUsedProducts.add(usedProduct3);

            UsedProduct usedProduct4 = new UsedProduct();
            usedProduct4.setId("575aa374229d425914c94610");
            usedProduct4.setMsrpCurrency(msrpCurrency);
            usedProduct4.setMsrp(234);
            usedProduct4.setBrandID("Hero");
            usedProduct4.setCreatedAt("2016/06/10");
            usedProduct4.setUpdatedAt("2016/06/10");
            usedProduct4.setCustomerID("575aa3742fcb7ea983ff4576");
            usedProduct4.setProductID("575a963098d67ff6c6e38cc9");
            usedProduct4.setWarrantyID("2016/06/10");

            mockUsedProducts.add(usedProduct4);
        }

        return mockUsedProducts;
    }


    public static List<UsedProduct> createUsedProducts(String brandID, String category){
        List<UsedProduct> usedProducts = new ArrayList<>();


        if (category.equals("electronics")){

            UsedProduct usedProduct5 = new UsedProduct();
            usedProduct5.setId("575aa374d0a3dddda3ccb2d11");
            usedProduct5.setMsrp(12000);
            usedProduct5.setBrandID("warratixID");
            usedProduct5.setCreatedAt("2016/06/10");
            usedProduct5.setUpdatedAt("2016/06/10");
            usedProduct5.setCustomerID("575aa374d06f7df6578904d6");
            usedProduct5.setProductID("575a963055b8439f41aac0ff1");
            usedProduct5.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct5);

            UsedProduct usedProduct6 = new UsedProduct();
            usedProduct6.setId("575aa374dbdbf1be418952931");
            usedProduct6.setMsrp(15000);
            usedProduct6.setBrandID("warratixID");
            usedProduct6.setCreatedAt("2016/06/10");
            usedProduct6.setUpdatedAt("2016/06/10");
            usedProduct6.setCustomerID("575aa3746a675ace7bc03e4c");
            usedProduct6.setProductID("575a96305764b195b7c0f7841");
            usedProduct6.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct6);

            UsedProduct usedProduct7 = new UsedProduct();
            usedProduct7.setId("575aa37428dd5e00c221e7a41");
            usedProduct7.setMsrp(15300);
            usedProduct7.setBrandID("warratixID");
            usedProduct7.setCreatedAt("2016/06/10");
            usedProduct7.setUpdatedAt("2016/06/10");
            usedProduct7.setCustomerID("575aa37475a3cfdef25965b4");
            usedProduct7.setProductID("575a9630d5725ffc91c74eab1");
            usedProduct7.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct7);


        } else if (category.equals("appliances")){

            UsedProduct usedProduct8 = new UsedProduct();
            usedProduct8.setId("575aa374c4fd4f639ed44b2f1");
            usedProduct8.setMsrp(11000);
            usedProduct8.setBrandID("warratixID");
            usedProduct8.setCreatedAt("2016/06/10");
            usedProduct8.setUpdatedAt("2016/06/10");
            usedProduct8.setCustomerID("575aa3747b1c545c44b3da9f");
            usedProduct8.setProductID("575a9630ac9474317edd8aab1");
            usedProduct8.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct8);

            UsedProduct usedProduct9 = new UsedProduct();
            usedProduct9.setId("575aa374229d425914c946101");
            usedProduct9.setMsrp(100000);
            usedProduct9.setBrandID("warratixID");
            usedProduct9.setCreatedAt("2016/06/10");
            usedProduct9.setUpdatedAt("2016/06/10");
            usedProduct9.setCustomerID("575aa3742fcb7ea983ff4576");
            usedProduct9.setProductID("575a963098d67ff6c6e38cc11");
            usedProduct9.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct9);

            UsedProduct usedProduct10 = new UsedProduct();
            usedProduct10.setId("575aa374d0a3dddda3ccb2d12");
            usedProduct10.setMsrp(100000);
            usedProduct10.setBrandID("warratixID");
            usedProduct10.setCreatedAt("2016/06/10");
            usedProduct10.setUpdatedAt("2016/06/10");
            usedProduct10.setCustomerID("575aa374d06f7df6578904d6");
            usedProduct10.setProductID("575a963055b8439f41aac0ff1");
            usedProduct10.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct10);

        } else if (category.equals("vehicles")){
            UsedProduct usedProduct11 = new UsedProduct();
            usedProduct11.setId("575aa374dbdbf1be418952932");
            usedProduct11.setMsrp(100000);
            usedProduct11.setBrandID("warratixID");
            usedProduct11.setCreatedAt("2016/06/10");
            usedProduct11.setUpdatedAt("2016/06/10");
            usedProduct11.setCustomerID("575aa3746a675ace7bc03e4c");
            usedProduct11.setProductID("575a96305764b195b7c0f78411");
            usedProduct11.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct11);

            UsedProduct usedProduct12 = new UsedProduct();
            usedProduct12.setId("575aa37428dd5e00c221e7a42");
            usedProduct12.setMsrp(100000);
            usedProduct12.setBrandID("warratixID");
            usedProduct12.setCreatedAt("2016/06/10");
            usedProduct12.setUpdatedAt("2016/06/10");
            usedProduct12.setCustomerID("575aa37475a3cfdef25965b4");
            usedProduct12.setProductID("575a9630d5725ffc91c74eab11");
            usedProduct12.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct12);

            UsedProduct usedProduct13 = new UsedProduct();
            usedProduct13.setId("575aa374c4fd4f639ed44b2f2");
            usedProduct13.setMsrp(100000);
            usedProduct13.setBrandID("warratixID");
            usedProduct13.setCreatedAt("2016/06/10");
            usedProduct13.setUpdatedAt("2016/06/10");
            usedProduct13.setCustomerID("575aa3747b1c545c44b3da9f");
            usedProduct13.setProductID("575a9630ac9474317edd8aab11");
            usedProduct13.setWarrantyID("2016/06/10");

            usedProducts.add(usedProduct13);
        }

        return  usedProducts;
    }

    public static List<Service> createServices(){

       if (mockServices.size() == 0) {

           Service service0 = new Service();
           service0.setId("575ace1ea6b98c418f0cb3b4");
           service0.setCreatedAt("2016-06-10");
           service0.setUpdatedAt("2016-06-10");
           service0.setBrandID("Hero");
           service0.setType("insurance");
           service0.setName("IFFCO TOKIO");
           service0.setServiceCompanyID("575acf2274550a837e5d9c92");
           service0.setPrice(390);
           service0.setCurrency("CAD");
           service0.setDeal(176);
           service0.setDescription("Veniam amet commodo sit non ipsum quis labore aliquip ad qui adipisicing exercitation.");
           service0.setDuration("10");

           mockServices.add(service0);

           Service service1 = new Service();
           service1.setId("575ace1eb10c0ee37376bb04");
           service1.setCreatedAt("2016-06-10");
           service1.setUpdatedAt("2016-06-10");
           service1.setBrandID("Hero");
           service1.setType("insurance");
           service1.setName("Tata AIG Insurance");
           service1.setServiceCompanyID("575acf2275674a353e016eb1");
           service1.setPrice(794);
           service1.setCurrency("EUR");
           service1.setDeal(736);
           service1.setDescription("Ex sit et aliqua fugiat nostrud eu nulla ut sunt commodo aliqua quis laborum non.");
           service1.setDuration("3");

           mockServices.add(service1);

//        Service service2 = new Service();
//        service2.setId("575ace1e651e7584f70664a8");
//        service2.setCreatedAt("2016-06-10");
//        service2.setUpdatedAt("2016-06-10");
//        service2.setBrandID("Hero");
//        service2.setType("insurance");
//        service2.setName("IFFCO TOKIO");
//        service2.setServiceCompanyID("575acf22b15e97e514ee09c9");
//        service2.setPrice(165);
//        service2.setCurrency("USD");
//        service2.setDeal(508);
//        service2.setDescription("Aute commodo adipisicing ut in sit cillum anim in consequat.");
//        service2.setDuration("5");
//
//        mockServices.add(service2);
//
//        Service service3 = new Service();
//        service3.setId("575ace1e1f5849436f5398c2");
//        service3.setCreatedAt("2016-06-10");
//        service3.setUpdatedAt("2016-06-10");
//        service3.setBrandID("Hero");
//        service3.setType("insurance");
//        service3.setName("Tata AIG Insurance");
//        service3.setServiceCompanyID("575acf22971c7d6ccacf371f");
//        service3.setPrice(540);
//        service3.setCurrency("AUD");
//        service3.setDeal(615);
//        service3.setDescription("Veniam Lorem minim anim irure sunt labore ullamco qui qui laboris.");
//        service3.setDuration("10");
//
//        mockServices.add(service3);
//
//        Service service4 = new Service();
//        service4.setId("575ace1e6b97709ffa66326d");
//        service4.setCreatedAt("2016-06-10");
//        service4.setUpdatedAt("2016-06-10");
//        service4.setBrandID("Hero");
//        service4.setType("insurance");
//        service4.setName("Tata AIG Insurance");
//        service4.setServiceCompanyID("575acf22c0f1ccff86fd9c74");
//        service4.setPrice(541);
//        service4.setCurrency("GBP");
//        service4.setDeal(662);
//        service4.setDescription("Et aute aliquip laboris minim duis eu consectetur est.");
//        service4.setDuration("4");
//
//        mockServices.add(service4);
//
//        Service service5 = new Service();
//        service5.setId("575ace1e50dcf4edb271a736");
//        service5.setCreatedAt("2016-06-10");
//        service5.setUpdatedAt("2016-06-10");
//        service5.setBrandID("Hero");
//        service5.setType("insurance");
//        service5.setName("Relince General");
//        service5.setServiceCompanyID("575acf22b5e0387fba50fa1e");
//        service5.setPrice(548);
//        service5.setCurrency("CAD");
//        service5.setDeal(196);
//        service5.setDescription("Elit id sint eiusmod esse ullamco sint incididunt nostrud occaecat elit sint sunt.");
//        service5.setDuration("5");
//
//        mockServices.add(service5);
//
//        Service service6 = new Service();
//        service6.setId("575ace1e21cf57f81a22d96f");
//        service6.setCreatedAt("2016-06-10");
//        service6.setUpdatedAt("2016-06-10");
//        service6.setBrandID("Hero");
//        service6.setType("insurance");
//        service6.setName("Relince General");
//        service6.setServiceCompanyID("575acf228194ac5d99933b3d");
//        service6.setPrice(530);
//        service6.setCurrency("CAD");
//        service6.setDeal(710);
//        service6.setDescription("Eu qui sit voluptate voluptate incididunt voluptate consectetur minim id duis irure non amet consequat.");
//        service6.setDuration("5");
//
//        mockServices.add(service6);
       }

        return mockServices;

    }

    public static List<Cart> createCarts(){

        if (mockCarts.size() == 0) {

            createContacts();

            Cart cart0 = new Cart();
            cart0.setId("cart0");
            cart0.setCreatedAt("2016-06-10");
            cart0.setUpdatedAt("2016-06-10");
            cart0.setCustomerID("575a9630846b3c68b1e0271c");
            cart0.setDeliveryAddress(mockContacts.get(0));
            cart0.setTotalAmount(123000);
            cart0.setCurrency("INR");

            mockCarts.add(cart0);

            Cart cart1 = new Cart();
            cart1.setId("cart1");
            cart1.setCreatedAt("2016-06-10");
            cart1.setUpdatedAt("2016-06-10");
            cart1.setCustomerID("575a9630f7baf774b1f23eab");
            cart1.setDeliveryAddress(mockContacts.get(1));
            cart1.setTotalAmount(234000);
            cart1.setCurrency("INR");

            mockCarts.add(cart1);

            Cart cart2 = new Cart();
            cart2.setId("cart2");
            cart2.setCreatedAt("2016-06-10");
            cart2.setUpdatedAt("2016-06-10");
            cart2.setCustomerID("575a9630d7e236aabef5edf8");
            cart2.setDeliveryAddress(mockContacts.get(2));
            cart2.setTotalAmount(123000);
            cart2.setCurrency("INR");

            mockCarts.add(cart2);
        }

        return mockCarts;
    }


    public static List<Contact> createContacts(){

        if (mockContacts.size() == 0) {


            Contact contact0 = new Contact();
            contact0.setFirstName("Matthews");
            contact0.setLastName("Barlow");
            contact0.setTel("+91 (900) 445-3807");
            contact0.setSsn("Gale");
            contact0.setAddress("757 Gallatin Place");
            contact0.setCity("Frystown");
            contact0.setZip(86351);
            contact0.setCountry("Faroe Islands");
            contact0.setContactEmail("gal@vis.com");
            contact0.setWebsite("Silvia.com");
            contact0.setLatitude(22.206727);
            contact0.setLongitude(79.51726);

            mockContacts.add(contact0);

            Contact contact1 = new Contact();
            contact1.setFirstName("Elliott");
            contact1.setLastName("Sloan");
            contact1.setTel("+91 (987) 519-2511");
            contact1.setSsn("Esmeralda");
            contact1.setAddress("689 Surf Avenue");
            contact1.setCity("Fedora");
            contact1.setZip(42554);
            contact1.setCountry("Swaziland");
            contact1.setContactEmail("esm@visua.com");
            contact1.setWebsite("Miranda.com");
            contact1.setLatitude(22.325343);
            contact1.setLongitude(77.392789);

            mockContacts.add(contact1);

            Contact contact2 = new Contact();
            contact2.setFirstName("Alexandra");
            contact2.setLastName("Lamb");
            contact2.setTel("+91 (990) 522-2052");
            contact2.setSsn("Debbie");
            contact2.setAddress("209 Celeste Court");
            contact2.setCity("Kapowsin");
            contact2.setZip(31776);
            contact2.setCountry("Gabon");
            contact2.setContactEmail("deb@vis.com");
            contact2.setWebsite("Slater.com");
            contact2.setLatitude(22.120678);
            contact2.setLongitude(75.463442);

            mockContacts.add(contact2);

            Contact contact3 = new Contact();
            contact3.setFirstName("Beach");
            contact3.setLastName("Shields");
            contact3.setTel("+91 (950) 402-3848");
            contact3.setSsn("Holt");
            contact3.setAddress("406 Clay Street");
            contact3.setCity("Escondida");
            contact3.setZip(45367);
            contact3.setCountry("Ethiopia");
            contact3.setContactEmail("hol@vis.com");
            contact3.setWebsite("Penelope.com");
            contact3.setLatitude(20.64305);
            contact3.setLongitude(79.668411);

            mockContacts.add(contact3);

            Contact contact4 = new Contact();
            contact4.setFirstName("Roslyn");
            contact4.setLastName("Jefferson");
            contact4.setTel("+91 (968) 506-2024");
            contact4.setSsn("Moore");
            contact4.setAddress("374 Verona Place");
            contact4.setCity("Bridgetown");
            contact4.setZip(39468);
            contact4.setCountry("Reunion");
            contact4.setContactEmail("moor@vis.com");
            contact4.setWebsite("Keller.com");
            contact4.setLatitude(24.11774);
            contact4.setLongitude(72.249163);

            mockContacts.add(contact4);

            Contact contact5 = new Contact();
            contact5.setFirstName("Regina");
            contact5.setLastName("Gaines");
            contact5.setTel("+91 (927) 498-3942");
            contact5.setSsn("Debra");
            contact5.setAddress("260 Aviation Road");
            contact5.setCity("Freelandville");
            contact5.setZip(10893);
            contact5.setCountry("Bosnia and Herzegovina");
            contact5.setContactEmail("debrag@vis.com");
            contact5.setWebsite("Rhoda.com");
            contact5.setLatitude(22.673356);
            contact5.setLongitude(32.945107);

            mockContacts.add(contact5);

            Contact contact6 = new Contact();
            contact6.setFirstName("Lowe");
            contact6.setLastName("Goff");
            contact6.setTel("+91 (992) 438-2760");
            contact6.setSsn("Tina");
            contact6.setAddress("441 Kansas Place");
            contact6.setCity("Blackgum");
            contact6.setZip(58621);
            contact6.setCountry("Canada");
            contact6.setContactEmail("tin@vis.com");
            contact6.setWebsite("Guy.com");
            contact6.setLatitude(25.919691);
            contact6.setLongitude(83.907874);

            mockContacts.add(contact6);
        }

        return mockContacts;
    }

    public static Cart getCart(String _Id){

        List<Cart> carts = new ArrayList<>();
        Cart selCart = new Cart();
        carts = createCarts();
        for (int i = 0; i < carts.size()-1; i++){
            Cart cart = carts.get(i);
            if (cart.getId().equals(_Id)){
                selCart = cart;
            }
        }
        return  selCart;
    }


    public static List<CustomerResponse> createCustomers(){

        if (mockCustomers.size() == 0) {

            createContacts();

            CustomerResponse customer0 = new CustomerResponse();
            customer0.setUpdatedAt("12/15/2015");
            customer0.setCustomerID("c0");
            customer0.setLeadBrand("Hero");
            customer0.setUsername("Srinivason.M.K");
            customer0.setFacebookID("1");
            customer0.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.person1).toString());
            customer0.setContact(mockContacts.get(0));

            mockCustomers.add(customer0);

            CustomerResponse customer1 = new CustomerResponse();
            customer1.setUpdatedAt("12/15/2015");
            customer1.setCustomerID("c1");
            customer1.setLeadBrand("Hero");
            customer1.setUsername("Ajay Patel");
            customer1.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.person2).toString());
            customer1.setContact(mockContacts.get(1));

            mockCustomers.add(customer1);

            CustomerResponse customer2 = new CustomerResponse();
            customer2.setUpdatedAt("12/15/2015");
            customer2.setFacebookID("2");
            customer2.setCustomerID("c2");
            customer2.setLeadBrand("Hero");
            customer2.setUsername("Rahul Kumar");
            customer2.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.person3).toString());
            customer2.setContact(mockContacts.get(2));

            mockCustomers.add(customer2);

            CustomerResponse customer3 = new CustomerResponse();
            customer3.setUpdatedAt("12/15/2015");
            customer3.setCustomerID("c3");
            customer3.setLeadBrand("Hero");
            customer3.setUsername("Shua Shar");
            customer3.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.person1).toString());
            customer3.setContact(mockContacts.get(3));

            mockCustomers.add(customer3);
        }

        return mockCustomers;

    }

    public static CustomerResponse getCustomerByID(String customerID){

        List<CustomerResponse> customerResponses = new ArrayList<>();
        CustomerResponse customerResponse = new CustomerResponse();
        customerResponses = createCustomers();
        if (customerResponses != null){
            for (int i = 0; i < customerResponses.size(); i++){
                CustomerResponse selCustomer = customerResponses.get(i);
                if (selCustomer.getCustomerID().equals(customerID)){
                    customerResponse = selCustomer;
                }
            }
        }

        return customerResponse;
    }

    public static List<String> creatBrandImgUrls(String type){

        if (mockBrandImgUrls.size() == 0) {

            if (type == null) {
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.warrantix0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.croma0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.big_bazaar0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.videocon0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.samsung0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.lg0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.suzuki0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tata0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.brand_cromax).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.reliance0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.flipkart0).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.zopper0).toString());
            } else {

                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_warrantix).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_hero).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_godrej).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_samsung).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_forbles).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_lg).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_mahindra).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_micromax).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_voltas).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_hero).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_godrej).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_warrantix).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_hero).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_godrej).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_samsung).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_forbles).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_lg).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_mahindra).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_micromax).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_voltas).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_hero).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_godrej).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_warrantix).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_hero).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_godrej).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_samsung).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_forbles).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_lg).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_mahindra).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_micromax).toString());
                mockBrandImgUrls.add(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tab_voltas).toString());

            }

        }


        return mockBrandImgUrls;
    }

    public static List<String> creatBrandImgUrlsBySearchword(String name){

            List<String> imageUrls = new ArrayList<>();

            List<Brand> brands = new ArrayList<>();

            Brand brand0 = new Brand();
            brand0.setName("warrantix");
            brand0.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.warrantix0).toString());
            brands.add(brand0);

            Brand brand1 = new Brand();
            brand1.setName("croma");
            brand1.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.croma0).toString());
            brands.add(brand1);

            Brand brand2 = new Brand();
            brand2.setName("bigbazaar");
            brand2.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.big_bazaar0).toString());
            brands.add(brand2);

            Brand brand3 = new Brand();
            brand3.setName("videocon");
            brand3.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.videocon0).toString());
            brands.add(brand3);

            Brand brand4 = new Brand();
            brand4.setName("samsung");
            brand4.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.samsung0).toString());
            brands.add(brand4);

            Brand brand5 = new Brand();
            brand5.setName("lg");
            brand5.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.lg0).toString());
            brands.add(brand5);

            Brand brand6 = new Brand();
            brand6.setName("suzuki");
            brand6.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.suzuki0).toString());
            brands.add(brand6);

            Brand brand7 = new Brand();
            brand7.setName("tata");
            brand7.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.tata0).toString());
            brands.add(brand7);

            Brand brand8 = new Brand();
            brand8.setName("cromax");
            brand8.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.brand_cromax).toString());
            brands.add(brand8);

            Brand brand9 = new Brand();
            brand9.setName("reliance");
            brand9.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.reliance).toString());
            brands.add(brand9);

            Brand brand10 = new Brand();
            brand10.setName("flipkart");
            brand10.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.flipkart).toString());
            brands.add(brand10);

            Brand brand11 = new Brand();
            brand11.setName("zopper");
            brand11.setImage(Uri.parse("android.resource://com.warrantix.main/" + R.drawable.zopper).toString());
            brands.add(brand11);

           for (int i = 0; i < brands.size(); i++){
               if (brands.get(i).getName().contentEquals(name)){
                   imageUrls.add(brands.get(i).getImage());
               }
           }

            return imageUrls;


    }

    public static List<ServiceCenter> createServiceCenters(){
        List<ServiceCenter> serviceCenters = new ArrayList<>();

        ServiceCenter serviceCenter0 = new ServiceCenter();
        serviceCenter0.setId("sc0");
        serviceCenter0.setName("Service 1");
        List<String> dates0 = new ArrayList<>();
        dates0.add("2016-07-11 11:35");
        dates0.add("2016-07-08 10:15");
        dates0.add("2016-07-04 03:05");
        serviceCenter0.setAvailability(dates0);

        serviceCenters.add(serviceCenter0);

        ServiceCenter serviceCenter1 = new ServiceCenter();
        serviceCenter1.setId("sc1");
        serviceCenter1.setName("Service 2");
        List<String> dates1 = new ArrayList<>();
        dates1.add("2016-07-13 01:35");
        dates1.add("2016-07-09 10:15");
        dates1.add("2016-07-05 05:55");
        serviceCenter1.setAvailability(dates1);

        serviceCenters.add(serviceCenter1);

        ServiceCenter serviceCenter2 = new ServiceCenter();
        serviceCenter2.setId("sc2");
        serviceCenter2.setName("Service 3");
        List<String> dates2 = new ArrayList<>();
        dates2.add("2016-07-10 09:35");
        dates2.add("2016-07-04 04:15");
        dates2.add("2016-07-01 10:55");
        serviceCenter2.setAvailability(dates2);

        serviceCenters.add(serviceCenter2);



        return serviceCenters;
    }

    public static ServiceCenter getServiceCetner(String serviceCenterID){
        ServiceCenter serviceCenter = new ServiceCenter();

        List<ServiceCenter> serviceCenters = createServiceCenters();
        for (int i = 0; i < serviceCenters.size(); i++){
            if (serviceCenters.get(i).getId().equals(serviceCenterID)){
                serviceCenter = serviceCenters.get(i);
            }
        }

        return serviceCenter;
    }

}
