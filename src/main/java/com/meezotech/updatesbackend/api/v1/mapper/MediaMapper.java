package com.meezotech.updatesbackend.api.v1.mapper;

import com.meezotech.updatesbackend.api.v1.model.MediaDTO;
import com.meezotech.updatesbackend.domain.Media;

import java.util.Set;

public interface MediaMapper {

    Media mediaDtoToMedia(MediaDTO mediaDTO);

    MediaDTO mediaToMediaDto(Media media);

    Set<Media> mediaDtoListToMediaList(Set<MediaDTO> mediaDTOS);

    Set<MediaDTO> mediaListToMediaDtoList(Set<Media> mediaSet);

}
