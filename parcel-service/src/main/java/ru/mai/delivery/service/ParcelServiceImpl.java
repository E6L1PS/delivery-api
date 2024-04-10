package ru.mai.delivery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mai.delivery.dto.ParcelDto;
import ru.mai.delivery.mapper.ParcelMapper;
import ru.mai.delivery.model.Parcel;
import ru.mai.delivery.repository.ParcelRepository;
import ru.mai.delivery.service.interfaces.ParcelService;

/**
 * Создан: 09.04.2024.
 *
 * @author Pesternikov Danil
 */
@RequiredArgsConstructor
@Service
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;

    private final ParcelMapper parcelMapper;

    @Override
    public Page<Parcel> findAll(Pageable pageable) {
        return parcelRepository.findAll(pageable);
    }

    @Override
    public Parcel findById(Long id) {
        return null;
    }

    @Override
    public void save(ParcelDto parcelDto) {
        Parcel parcel = parcelMapper.parcelDtoToParcel(parcelDto);
        parcelRepository.save(parcel);
    }

    @Override
    public void update(ParcelDto parcelDto) {
        Parcel parcel = parcelMapper.parcelDtoToParcel(parcelDto);
        parcelRepository.save(parcel);
    }

    @Override
    public void delete(String id) {
        parcelRepository.deleteById(id);
    }

}
