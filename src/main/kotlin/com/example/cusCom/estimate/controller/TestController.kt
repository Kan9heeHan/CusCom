package com.example.cusCom.estimate.controller

import com.example.cusCom.estimate.dto.Estimate
import com.example.cusCom.estimate.dto.parts.CPU
import com.example.cusCom.estimate.exception.EstimateException
import com.example.cusCom.estimate.service.DesktopPartsService
import com.example.cusCom.estimate.service.EstimateService
import com.example.cusCom.estimate.service.parts.*
import com.google.gson.Gson
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class TestController(private val desktopPartsService: DesktopPartsService,
                     private val estimateService: EstimateService) {

    @GetMapping("/test")
    fun getDataTest(model:Model):String{
        model.addAttribute("caseList",desktopPartsService.getCaseList())
        model.addAttribute("cpuCoolerList",desktopPartsService.getCpuCoolerList())
        model.addAttribute("cpuList",desktopPartsService.getCPUList())
        model.addAttribute("dataStorageList",desktopPartsService.getDataStorageList())
        model.addAttribute("graphicsCardList",desktopPartsService.getGraphicsCardList())
        model.addAttribute("memoryList",desktopPartsService.getMemoryList())
        model.addAttribute("motherBoardList",desktopPartsService.getMotherBoardList())
        model.addAttribute("powerSupplyList",desktopPartsService.getPowerSupplyList())
        return "customPage"
    }

    @GetMapping("/postTest")
    fun dataTest():String{
        return "dataInputPage"
    }

    @PostMapping("/postTest")
    fun postDataTest(@RequestParam("CPU") cpuJSON:String):String{
        println(cpuJSON)
        val tempCPU = Gson().fromJson(cpuJSON,CPU::class.java)
        desktopPartsService.createCPU(tempCPU)
        return "redirect:/test"
    }

    @GetMapping("/clear")
    fun testClearPage(){}
    @GetMapping("/fail")
    fun testFailPage(){}

    //@ExceptionHandler(EstimateException::class)
    //fun estimateException(ex:EstimateException,model:Model):String{
    //    model.addAttribute("errorMsg",ex.message)
    //    return "failView"
    //}
}