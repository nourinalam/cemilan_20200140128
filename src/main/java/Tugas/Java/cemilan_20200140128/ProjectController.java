/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tugas.Java.cemilan_20200140128;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author user
 */
@Controller
public class ProjectController {
    
    private int hitungHargaTotal(int harga, int jumlah){
        int total = harga * jumlah;
        return total;
    }
    
    private int hitungHargaAkhir(int total){
        int hargaAkhir = 0;        
        if (total < 16000){
            hargaAkhir = total - (total * 0);
        }
        else if (total > 16000 && total < 25000){
            hargaAkhir = total - (total * 10 / 100);
        }
        else if (total > 25000){
            hargaAkhir = total - (total * 15 / 100);
        }
           
        return hargaAkhir;
    }
    
    private int hitungDiskon(int total){
        int diskon = 0;        
        if (total < 16000){
            diskon = 0;
        }
        else if (total > 16000 && total < 25000){
            diskon = 10;
        }
        else if (total > 25000){
            diskon = 15;
        }
        
        return diskon;
    }
    
    @RequestMapping("/input")
    //@ResponseBody
    public String getHasil(HttpServletRequest data, Model datas){      
        
        
        String getNama = data.getParameter("nmSayur");
        int getHarga = Integer.parseInt(data.getParameter("harga"));
        int getJumlah = Integer.parseInt(data.getParameter("jumlah"));
        int getBayar = Integer.parseInt(data.getParameter("bayar"));
        
        
        int total =  hitungHargaTotal(getHarga, getJumlah);
        int jumlah_bayar = hitungHargaAkhir(total);
        int diskon = hitungDiskon(total);
        int total_harga_diskon = total - jumlah_bayar;
        int uang_bayar = getBayar;
        int uang_kembali = uang_bayar - jumlah_bayar;
        
        String kembalian = null;        
        if(uang_kembali==0){
            kembalian = "UANG ANDA PAS!!";
        }else if(uang_kembali>0){
            kembalian = Integer.toString(uang_kembali);
        }
        
        datas.addAttribute("nama_sayur", getNama);
        datas.addAttribute("harga_perkilo", getHarga);
        datas.addAttribute("jumlah_beli", getJumlah);
        datas.addAttribute("total", total);
        datas.addAttribute("diskon", diskon);
        datas.addAttribute("total_harga_diskon", total_harga_diskon);
        datas.addAttribute("jumlah_bayar", jumlah_bayar);
        datas.addAttribute("uang_bayar", uang_bayar);
        datas.addAttribute("kembalian", kembalian);
        
        if(uang_kembali<0){  
            datas.addAttribute("kurang", uang_kembali);
            return "uangkurang";
        }else{            
            return "result";
        }
        
    }
    

    
}