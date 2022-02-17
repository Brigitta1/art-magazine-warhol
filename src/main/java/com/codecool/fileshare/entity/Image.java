package com.codecool.fileshare.entity;

import lombok.Data;

import java.util.UUID;

@Data
public class Image {

    private UUID uuid;
    private String category;
    private byte[] content;
    private String extension;
}
