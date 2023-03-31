/* -*-c++-*-
 * mbox.h
 * Copyright(c) 2022 Dongsoo S. Kim
 */
#include <stdint.h>

#ifndef MBOX_H
#define MBOX_H


bool mbox_empty(uint16_t mid);
bool mbox_avail(uint16_t mid);

int mbox_send(uint16_t mid, const void* packet, int len);
int mbox_recv(uint16_t mid, void* packet, int max);

#endif