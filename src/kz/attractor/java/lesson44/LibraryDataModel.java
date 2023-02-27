package kz.attractor.java.lesson44;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LibraryDataModel {
    private BookData books;
    private UserData userData;
    private List<UserData> userDataList = new ArrayList<>();
    private List<BookData> bookDataList = new ArrayList<>();

    public LibraryDataModel() {
      this.userDataList= FileService.readUsers();
      this.bookDataList= FileService.readBooks();
       this.books = new BookData(1, LocalDate.now(), "test testetstetext",true);
      this.userData = new UserData("1s@mail.ru", "Bill","1");

    }

    public BookData getBooks() {
        return books;
    }

    public void setBooks(BookData books) {
        this.books = books;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }

    public List<BookData> getBookDataList() {
        return bookDataList;
    }

    public void setBookDataList(List<BookData> bookDataList) {
        this.bookDataList = bookDataList;
    }
}
