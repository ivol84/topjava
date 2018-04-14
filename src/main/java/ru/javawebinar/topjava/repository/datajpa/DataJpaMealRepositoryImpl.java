package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && loadUserForMeal(meal).orElse(new User()).getId() != userId) {
            return null;
        }
        User user = userRepository.get(userId);
        meal.setUser(user);
        return  crudRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.deleteUserMeal(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.findUserMeal(id, userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findUserMeals(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.findBetween(startDate, endDate, userId);
    }

    private Optional<User> loadUserForMeal(Meal meal) {
        Assert.notNull(meal.getId(), "Use only with existing meal");
        Meal loadedMeal = crudRepository.findById(meal.getId()).orElse(new Meal());
        return Optional.ofNullable(loadedMeal.getUser());
    }
}
