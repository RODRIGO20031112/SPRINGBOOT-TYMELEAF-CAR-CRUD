package br.com.fiap.service;

import br.com.fiap.model.Car;
import br.com.fiap.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

  @Autowired
  private CarRepository carRepository;
  public List<Car> findAll() {
    return carRepository.findAll();
  }

  public void save(Car car) {
    carRepository.save(car);
  }

  public Optional<Car> findById(Long id) {
    return carRepository.findById(id);
  }

  public void deleteById(Long id) {
    carRepository.deleteById(id);
  }
}
