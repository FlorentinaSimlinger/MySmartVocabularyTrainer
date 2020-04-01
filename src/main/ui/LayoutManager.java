package ui;

import javafx.scene.control.TableView;
import model.SingleEntry;

public enum LayoutManager {
    INSTANCE;

    private LayoutManager() {
        AboutLayout aboutLayout = new AboutLayout();
        DatabaseLayout databaseLayout = new DatabaseLayout();
        LoginLayout loginLayout = new LoginLayout();
        MainLayout mainLayout = new MainLayout();
        ProfileLayout profileLayout = new ProfileLayout();
        RootLayout rootLayout = new RootLayout();
        SearchLayout searchLayout = new SearchLayout();
        TestLayout testLayout = new TestLayout();
    }


    public void doSomething() {

    }

    private TableView<SingleEntry> table;

    public synchronized TableView<SingleEntry> getTable() {
        return table;
    }

    public synchronized AboutLayout getAboutLayout() {
        return aboutLayout;
    }

    public synchronized DatabaseLayout getDatabaseLayout() {
        return databaseLayout;
    }
}
