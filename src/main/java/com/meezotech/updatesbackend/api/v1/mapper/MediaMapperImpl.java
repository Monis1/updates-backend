package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.MediaDTO;
import com.meezotech.updatesbackend.domain.Media;
import com.meezotech.updatesbackend.domain.MediaType;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class MediaMapperImpl implements MediaMapper {

    @Override
    public Media mediaDtoToMedia(MediaDTO mediaDTO) {
        if (mediaDTO == null) {
            return null;
        }
        Media media = new Media();
        media.setMediaType(MediaType.valueOf(mediaDTO.getMediaType()));
        media.setUrl(mediaDTO.getUrl());

        return media;
    }

    @Override
    public MediaDTO mediaToMediaDto(Media media) {
        if (media == null) {
            return null;
        }
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setMediaType(media.getMediaType().name());
        mediaDTO.setUrl(media.getUrl());

        return mediaDTO;
    }

    @Override
    public Set<Media> mediaDtoListToMediaList(Set<MediaDTO> mediaDTOS) {
        if (mediaDTOS == null) {
            return null;
        }
        Set<Media> media = new HashSet<>();
        for (MediaDTO mediaDTO :
                mediaDTOS) {
            media.add(mediaDtoToMedia(mediaDTO));
        }
        return media;
    }

    @Override
    public Set<MediaDTO> mediaListToMediaDtoList(Set<Media> mediaSet) {
        if (mediaSet == null) {
            return null;
        }
        Set<MediaDTO> mediaDTOS = new HashSet<>();
        for (Media media:
             mediaSet) {
            mediaDTOS.add(mediaToMediaDto(media));
        }
        return  mediaDTOS;
    }

}
