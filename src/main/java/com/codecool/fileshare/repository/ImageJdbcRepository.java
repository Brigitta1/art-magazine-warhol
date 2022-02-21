package com.codecool.fileshare.repository;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String[] parts = content.split(",");
        String imageString = parts[1];
        byte[] imageByteArray = Base64.decode(imageString);

        int a = content.indexOf("/");
        int b = content.indexOf(";");
        String extension = content.substring(a+1, b);

        String query = "INSERT INTO image VALUES(?, ?, ?, ?)";
        try (Connection connection = db.getDataSource().getConnection()){
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setObject(1, uuid);
            ps.setString(2, category);
            ps.setBytes(3, imageByteArray);
            ps.setString(4, extension);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
        // implement store image in database here
        // content is base64 coded image file
        // generate and return uuid of stored image
        // https://www.base64-image.de/
        // https://codebeautify.org/base64-to-image-converter
    }

    @Override
    public String readImage(String uuid) {
        UUID uuid1 = UUID.fromString(uuid);
        String query = "SELECT content FROM image WHERE id = ?";
        String result = "Wrong uuid!";
        try (Connection connection = db.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setObject(1, uuid1);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                result = new String(Base64.encode(rs.getBytes("content")));
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
        // implement readImage from database here
        // return base64 encoded image
    }
}
