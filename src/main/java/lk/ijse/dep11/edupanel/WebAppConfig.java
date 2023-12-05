package lk.ijse.dep11.edupanel;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@EnableWebMvc
@ComponentScan
@Configuration
public class WebAppConfig {

    @Bean
    public Bucket defaultBucket() throws IOException {
        InputStream serviceAccount = getClass()
                .getResourceAsStream("/edupanel-91ed5-firebase-adminsdk-34zas-f71f298e21.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount)).setStorageBucket("edupanel-91ed5.appspot.com")
                .build();

        FirebaseApp.initializeApp(options);

        return StorageClient.getInstance().bucket();
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
}
