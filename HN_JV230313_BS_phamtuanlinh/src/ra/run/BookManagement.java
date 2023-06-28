package ra.run;

import ra.model.Catalog;
import ra.model.Product;
import ra.service.CatalogService;
import ra.service.ProductService;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookManagement {
    private static CatalogService catalogService = new CatalogService();
    private static ProductService productService = new ProductService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            choice = displayMainMenu();
            switch (choice) {
                case 1:
                    catalogManagement();
                    break;
                case 2:
                    productManagement();
                    break;
                case 3:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 3);
    }


    private static int displayMainMenu() {
        System.out.println("**************************BASIC-MENU**************************");
        System.out.println("1. Quản lý danh mục");
        System.out.println("2. Quản lý sản phẩm");
        System.out.println("3. Thoát ");
        System.out.println("***************************************************************");
        System.out.println("Vui lòng chọn:");
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = 0;
        } finally {
            scanner.nextLine();
        }
        System.out.println();
        return choice;
    }

    private static void catalogManagement() {
        int choice;
        do {
            choice = displayCatalogManagementMenu();
            switch (choice) {
                case 1:
                    addCatalog();
                    break;
                case 2:
                    displayAllCatalogs();
                    break;
                case 3:
                    updateCatalogName();
                    break;
                case 4:
                    deleteCatalog();
                    break;
                case 5:
                    System.out.println("Quay lại");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 5);
    }

    public static int displayCatalogManagementMenu() {
        System.out.println("********************CATALOG-MANAGEMENT********************");
        System.out.println("1. Nhập số danh mục thêm mới và nhập thông tin cho từng danh mục");
        System.out.println("2. Hiển thị thông tin tất cả các danh mục ");
        System.out.println("3. Sửa tên danh mục theo mã danh mục");
        System.out.println("4. Xóa danh muc theo mã danh mục (lưu ý ko xóa khi có sản phẩm)");
        System.out.println("5. Quay lại");
        System.out.println("**********************************************************");
        System.out.println("Vui lòng chọn: ");
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            choice = 0;
        } finally {
            scanner.nextLine();
        }
        System.out.println();
        return choice;
    }

    public static void addCatalog() {
        System.out.println("Nhập số danh mục cần thêm mới: ");
        int numCatalogs = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i <= numCatalogs; i++) {
            System.out.println("Danh mục " + i);
            System.out.println("Nhập mã danh mục: ");
            int catalogID = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Nhập tên danh mục: ");
            String catalogName = scanner.nextLine();
            Catalog catalog = new Catalog(catalogID, catalogName);
            catalogService.save(catalog);
            System.out.println("Danh mục đã được thêm vào.");
        }
        System.out.println();
    }

    private static void displayAllCatalogs() {
        System.out.println("Thông tin tất cả các danh mục:");
        for (Catalog catalog : catalogService.getAll()) {
            System.out.println(catalog);
        }
        System.out.println();
    }

    public static void updateCatalogName() {
        System.out.println("Nhập mã danh mục cần sửa tên: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine();
        Catalog catalog = findCatalogById(catalogId);
        if (catalog != null) {
            System.out.println("Nhập tên mới cho danh mục: ");
            String newCatalogName = scanner.nextLine();
            catalog.setCatalogName(newCatalogName);
            System.out.println("Tên danh mục đã được cập nhật.");
        }
        System.out.println();
    }

    private static void deleteCatalog() {
        System.out.println("Nhập mã danh mục cần xóa: ");
        int catalogId = scanner.nextInt();
        scanner.nextLine();
        Catalog catalog = findCatalogById(catalogId);
        if (catalog != null) {
            if (checkIfCatalogHasProducts(catalog)) {
                System.out.println("Không thể xóa danh mục này vì có sản phẩm liên quan.");
            } else {
                catalogService.delete(catalogId);
            }
        }
        System.out.println();
    }

    public static boolean checkIfCatalogHasProducts(Catalog catalog) {
        for (Product product : productService.getAll()) {
            if (product.getCatalog().getCatalogId() == catalog.getCatalogId()) {
                return true;
            }
        }
        return false;
    }

    public static Catalog findCatalogById(int catalogId) {
        for (Catalog catalog : catalogService.getAll()) {
            if (catalog.getCatalogId() == catalogId) {
                return catalog;
            }
        }
        System.out.println("Không tìm thấy danh mục.");
        return null;
    }

    private static void productManagement() {
        int choice;
        do {
            choice = displayProductManagementMenu();
            switch (choice){
                case 1:
                    addProduct();
                    break;
                case 2:
                    displayAllProducts();
                    break;
                case 3:
                    sortProductsByPriceDescending();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    searchProductByName();
                    break;
                case 6:
                   updateProductInfo();
                    break;
                case 7:
                    System.out.println("Quay lại");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        }while (choice != 7);
    }

    public static int displayProductManagementMenu(){
        System.out.println("********************PRODUCT-MANAGEMENT********************");
        System.out.println("1. Nhập số sản phẩm và nhập thông tin sản phẩm");
        System.out.println("2. Hiển thị thông tin các sản phẩm");
        System.out.println("3. Sắp xếp sản phẩm theo giá giảm dần");
        System.out.println("4. Xóa sản phẩm theo mã");
        System.out.println("5. Tìm kiếm sách theo tên sách");
        System.out.println("6. Thay đổi thông tin của sách theo mã sách");
        System.out.println("7. Quay lại");
        System.out.println("**********************************************************");
        System.out.println("Vui lòng chọn:");
        int choice;
        try {
            choice= scanner.nextInt();
        }catch (InputMismatchException e){
            choice = 0;
        }finally {
            scanner.nextLine();
        }
        System.out.println();
        return choice;
    }
    public static void addProduct() {
    }
    public static void displayAllProducts() {
    }
    public static void sortProductsByPriceDescending(){

    }
    public static void deleteProduct() {
    }
    public static void searchProductByName() {
    }
    public static void updateProductInfo(){}
}
