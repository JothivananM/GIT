package com.SpringBoot.Demo.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class SidebarMenuDTO {

    private String url;

    private String menuName;

    private String icon;

    @Override
    public String toString() {
        return "SidebarMenuDTO [icon=" + icon + ", menuName=" + menuName + ", url=" + url + "]";
    }     

}
