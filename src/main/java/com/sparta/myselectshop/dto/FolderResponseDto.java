package com.sparta.myselectshop.dto;

import com.sparta.myselectshop.entity.Folder;
import lombok.Getter;

@Getter
public class FolderResponseDto {
    private final Long id;
    private final String name;

    public FolderResponseDto(Folder folder) {
        this.id = folder.getId();
        this.name = folder.getName();
    }
}