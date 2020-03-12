package chess;

public class Config {
    //Singleton instance field
    private static Config instance;

    private Config() {

    }

    //Singleton
    public Config Instance() {
        if(instance==null)
            instance=new Config();

        return instance;
    } 
}