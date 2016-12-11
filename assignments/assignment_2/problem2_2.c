#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char* SUIT[] = { "Clubs", "Diamonds", "Hearts", "Spades" };
char* FACE[] = { "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

struct card_t{
    char face[10];
    char suit[10];
};

void fill_deck(struct card_t**);
void print_deck(struct card_t** deck);
void shuffle_deck(struct card_t** deck);

void main()
{
    struct card_t* deck[52];
    fill_deck(deck);
    print_deck(deck);
    shuffle_deck(deck);
    print_deck(deck);
}

void fill_deck(struct card_t** deck) {

    int i, j, curr_card, total;
    int face_size = sizeof(FACE)/sizeof(FACE[0]);
    int suit_size = sizeof(SUIT)/sizeof(SUIT[0]);

    for (i = curr_card = 0; i < 13 ; ++i) {
        for (j = 0; j < 4; ++j, curr_card++) {
            deck[curr_card] = malloc(sizeof(struct card_t));
            strncpy(deck[curr_card]->face , FACE[i], 10);
            strncpy(deck[curr_card]->suit , SUIT[j], 10);
        }
    }
}


void print_deck(struct card_t** deck) {
    int i, j, curr_card;
    printf("\n");
    for (i = curr_card = 0; i < 13 ; ++i) {
        for (j = 0; j < 4; ++j, curr_card++) {
            printf("%*s of %s", 10, deck[curr_card]->face, deck[curr_card]->suit);
        }
        printf("\n");
    }
}

void shuffle_deck(struct card_t** deck) {
    int dest_card;
    struct card_t* tmp;

    int i, j, curr_card;
    for (i = curr_card = 0; i < 13 ; ++i) {
        for (j = 0; j < 4; ++j, curr_card++) {
            dest_card = rand() % 52;
            tmp = deck[curr_card];
            deck[curr_card] = deck[dest_card];
            deck[dest_card] = tmp;
        }
    }
}
