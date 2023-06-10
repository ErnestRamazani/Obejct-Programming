#ifndef ENGINE_H
#define ENGINE_H

#include <SFML/Graphics.hpp>
#include <SFML/Audio.hpp>
#include<vector>
#include "Entity.h"

class Engine {
private:
    sf::RenderWindow window;
    Entity* entity;
    std::vector<Obstacle*> obstacles; 
    sf::Sprite background;
    sf::SoundBuffer buffer;
    sf::Sound sound;
    sf::Clock timer;
    float acceleration;
    sf::Font font;
    sf::Text scoreText;
    sf::Text levelText;
    int score;
    int level;

public:
    Engine();
    ~Engine();

    void run();
};

#endif
