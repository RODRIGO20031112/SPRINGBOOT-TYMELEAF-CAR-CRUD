package br.com.fiap.controller;

import br.com.fiap.model.Car;
import br.com.fiap.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class CarController {

  @Autowired
  private CarService carService;

  @GetMapping("/")
  public String home(Model model) {
    return "redirect:/cars";
  }

  @GetMapping("/cars")
  public String listCar(Model model) {
    model.addAttribute("cars", carService.findAll());
    return "cars/list";
  }

  @GetMapping("/cars/create")
  public String showCreateForm(Model model) {
    model.addAttribute("car", new Car());
    return "cars/form";
  }

  @PostMapping("/cars")
  public String saveCar(@ModelAttribute Car car, @RequestParam("imageFile") MultipartFile imageFile) {
    if (!imageFile.isEmpty()) {
      try {
        byte[] imageBytes = imageFile.getBytes();
        car.setImage(imageBytes);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    carService.save(car);
    return "redirect:/cars";
  }

  @GetMapping("/cars/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    Car car = carService.findById(id).orElseThrow(() -> new IllegalArgumentException("Carro não encotrado Id: " + id));
    model.addAttribute("car", car);
    return "cars/form";
  }

  @GetMapping("/cars/update/{id}")
  public String updateCar(@PathVariable Long id, @ModelAttribute Car car) {
    carService.save(car);
    return "cars/form";
  }

  @GetMapping("/cars/delete/{id}")
  public String deleteCar(@PathVariable Long id) {
    carService.deleteById(id);
    return "redirect:/cars";
  }
}
