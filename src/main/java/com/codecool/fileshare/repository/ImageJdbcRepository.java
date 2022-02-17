package com.codecool.fileshare.repository;

import com.codecool.fileshare.entity.Image;
import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;


@Component("jdbc")
public class ImageJdbcRepository implements ImageRepository{

    DB db = new DB();
    UUID uuid;

    @Override
    public String storeImage(String category, String content) {
        uuid = UUID.nameUUIDFromBytes(content.getBytes());
        String id = uuid.toString();
        System.out.println(id);
        String delims = "[,]";
        String[] parts = content.split(delims);
        String imageString = parts[1];
        byte[] imageByteArray = Base64.decode(imageString);
        InputStream is = new ByteArrayInputStream(imageByteArray);
        String mimeType = null;
        String fileExtension = null;
        try {
            mimeType = URLConnection.guessContentTypeFromStream(is); //mimeType is something like "image/jpeg"
            String delimiter="[/]";
            String[] tokens = mimeType.split(delimiter);
            fileExtension = tokens[1];
        } catch (IOException ioException){
            ioException.printStackTrace();
        }
        String query = "INSERT INTO image VALUES(?, ?, ?, ?)";
        try (Connection connection = db.getDataSource().getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setObject(1, uuid);
            ps.setString(2, category);
            ps.setBytes(3, imageByteArray);
            ps.setString(4, fileExtension);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // implement store image in database here
        // content is base64 coded image file
        // generate and return uuid of stored image
        // https://www.base64-image.de/
        // https://codebeautify.org/base64-to-image-converter
        return id;
    }

    @Override
    public String readImage(String uuid) {
        // implement readImage from database here
        // return base64 encoded image
        return null;
    }
}
