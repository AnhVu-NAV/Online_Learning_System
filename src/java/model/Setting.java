/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Setting {
    private int id;             // ID của setting
    private int settingTypeId;   // ID loại setting
    private String value;        // Giá trị của setting
    private int status;          // Trạng thái setting
    private String description;  // Mô tả

    // Hàm khởi tạo mặc định (sinh ra từ @NoArgsConstructor)
    // Hàm khởi tạo có tham số (sinh ra từ @AllArgsConstructor)
}
