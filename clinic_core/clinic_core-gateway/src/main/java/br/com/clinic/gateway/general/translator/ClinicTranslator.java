package br.com.clinic.gateway.general.translator;

import java.util.List;

public interface ClinicTranslator<T, E> {

    T toEntity(final E object);

    E toDTO(final T object);

    List<T> toEntityList(final List<E> object);

    List<E> toDTOList(final List<T> object);

}
