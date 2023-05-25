package lab.space.vilki_palki_rest.mapper;

import lab.space.vilki_palki_rest.entity.Admin;
import org.springframework.data.domain.Page;

import lab.space.vilki_palki_rest.model.admin.AdminResponse;
import lab.space.vilki_palki_rest.model.admin.AdminResponseByPage;


public interface AdminMapper {
    static AdminResponseByPage toAdminsResponseByPage(Page<Admin> admins) {
        return AdminResponseByPage.builder()
                .data(admins.stream().map(AdminMapper::toDto).toList())
                .pagesCount(admins.getTotalPages())
                .itemsCount(admins.getTotalElements())
                .build();
    }

    static AdminResponse toDto(Admin admin) {
        return AdminResponse.builder()
                .id(admin.getId())
                .email(admin.getEmail())
                .firstname(admin.getFirstname())
                .lastname(admin.getLastname())
                .securityLevel(admin.getSecurityLevel())
                .build();
    }
}
