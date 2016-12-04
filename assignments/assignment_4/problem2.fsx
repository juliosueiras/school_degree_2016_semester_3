(* Author: Julio Tain Sueiras *)

let rec modulus value divisor =
    match divisor with
    | 0 ->
        0
    | _ ->
        let neg : bool = (value < 0)
        let value = abs(value)
        let divisor = abs(divisor)

        if value < divisor then
            if neg then -value else value
        else
            if neg then -(modulus (value-divisor) divisor) else modulus (value-divisor) divisor

printf "Result:%d" (modulus 3 2)

(* Testing *)
for a in 1 .. 30 do
    let curr_rand = System.Random().Next()
    let actual = modulus curr_rand a
    let expected = curr_rand % a

    if actual <> expected then
        printfn "A Test has failed: Current:%d, Random:%d, Actual: %d, Expected: %d" a curr_rand actual expected



