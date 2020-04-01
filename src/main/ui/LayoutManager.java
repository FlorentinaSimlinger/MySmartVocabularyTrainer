package ui;

import model.Profile;

//represents a layout manager using the singleton pattern with enumeration
//SOURCE: https://dzone.com/articles/java-singletons-using-enum
public enum LayoutManager {
    INSTANCE;

    private AboutLayout aboutLayout = new AboutLayout();
    private DatabaseLayout databaseLayout = new DatabaseLayout();
    private LoginLayout loginLayout = new LoginLayout();
    private MainLayout mainLayout = new MainLayout();
    private ProfileLayout profileLayout = new ProfileLayout();
    private RootLayout rootLayout = new RootLayout();
    private SearchLayout searchLayout = new SearchLayout();
    private TestLayout testLayout = new TestLayout();
    private Profile profile;
    private int testInt = 1;

    public int getTestInt() {
        return testInt;
    }

    public void setTestInt(int i) {
        this.testInt = i;
    }


    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public synchronized Profile getProfile() {
        return profile;
    }

    public synchronized AboutLayout getAboutLayout() {
        return aboutLayout;
    }

    public synchronized DatabaseLayout getDatabaseLayout() {
        return databaseLayout;
    }

    public synchronized LoginLayout getLoginLayout() {
        return loginLayout;
    }

    public synchronized MainLayout getMainLayout() {
        return mainLayout;
    }

    public synchronized ProfileLayout getProfileLayout() {
        return profileLayout;
    }

    public synchronized RootLayout getRootLayout() {
        return rootLayout;
    }

    public synchronized SearchLayout getSearchLayout() {
        return searchLayout;
    }

    public synchronized TestLayout getTestLayout() {
        return testLayout;
    }
}
