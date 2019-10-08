	org	8h
	db	1,0
	org	0ch
	ld	0f8h,#0       ; set port 0 to output mode
	ld	0f6h,#0       ; set port 2 to output mode
	ld	0fbh,#0       ; disable interrupts
	ld	0f4h,#0ffh    ; set timer 0 count
	ld	0f5h,#5       ; set prescaler
	ld	0f1h,#3       ; load timer and start
	ld	0ffh,#30h     ; set stack pointer
	ld	0fdh,#0       ; set working register pointer
        jp      start         ; jump to start
	org	100h
	iret                  ; return from interrupt
	org     200h
start:  ld	0,#0          ; set controls to low
	ld	2,#0aeh       ; write to displays
	or	0,#6          ; set display latches low
	call	sendstart     ; send start code
	ld	2,#0a0h       ; put status code into port 2
	call    sendbyte      ; send byte
	ld	2,#0          ; put high address into port 2
	call    sendbyte      ; send byte
	ld	2,#1          ; put low address into port 2
	call    sendbyte      ; send byte
	call	sendstart     ; send start code
	ld	2,#0a1h       ; put status code to read
	call	sendbyte      ; send byte
	call	recvbyte      ; receive byte
	call    sendack       ; send acknowledge
	ld	0,#0          ; enable displays
	ld	2,9           ; write data to displays
	ld	0,#6          ; latch displays
	call	recvbyte      ; receive byte
	call    sendack       ; send acknowledge
	ld	0,#0          ; enable displays
	ld	2,9           ; write data to displays
	ld	0,#6          ; latch displays
	call	sendstop      ; send stop code

	call	sendstart     ; send start code
	ld	2,#0a0h       ; status code to write
	call	sendbyte      ; send the byte
	ld	2,#0          ; high part of address
	call	sendbyte      ; send it
	ld	2,#0          ; low part of address
	call	sendbyte      ; send it
	ld	2,#0beh       ; byte to write to memory
	call	sendbyte      ; send it
	call    sendstop      ; send stop code
	call	sendstart     ; send start code
	ld	2,#0a0h       ; status code for write
	call	sendbyte      ; send it
	ld	2,#0          ; high byte of address
	call    sendbyte      ; send it
	ld	2,#0          ; low byte of address
	call	sendbyte      ; send it
	call	sendstart     ; send start code
	ld	2,#0a1h       ; function to read
	call	sendbyte      ; send it
	call	recvbyte      ; receive byte
	call	sendack       ; send acknowledge
	ld	2,9           ; transfer read byte to output port
	ld	0,#0          ; enable displays
	ld	0,#6          ; latch data
	call	sendstop      ; **************************************
	halt

sendstart:
	ld	0f6h,#0       ; Set port 2 to output mode
	and	00,#0feh      ; be sure eprom clock is off
	ld	02,#1         ; set data line high
	or	00,#1         ; set clock high
	and	02,#0feh      ; reset clock
	and	00,#0feh      ; reset data line
	ret
sendstop:
	ld	2,#0          ; need a low on data port
	or	0,#1          ; set eeprom clock high
	ld	2,#1          ; signal stop condition
	and	0,#0feh       ; set eeprom clock low
	ret                   ; and return to caller
sendbyte:
	push	10            ; save register
	ld	10,#8         ; 8 bits to sind
wrloop:	rl	2             ; rotate next bit in output port
	or	0,#1          ; set eeprom clock on
	nop                   ; provide some time
	and	0,#0feh       ; turn off eeprom clock
	dec	10            ; decrement counter
	jp	nz,wrloop     ; jump if not done
	pop	10            ; recover used register
	or	0,#1          ; set eeprom clock high
	nop                   ; provide some time
	and	0,#0feh       ; turn off clock
	ret                   ; return to caller
recvbyte:
	ld	0f6h,#0ffh    ; set port 2 to input mode
	push	10            ; save consumed registers
	push	11
	push	12
	ld	10,#8         ; 8 bits to receive
	ld	12,#0         ; set accumulator to zeor
rxloop:	or	0,#1          ; set eeprom clock high
	ld	11,2          ; get byte from port
	rl	12            ; rotate accumulator
	and	11,#1         ; keep only bit 0
	or	12,11         ; combine with accumulator
	and	0,#0feh       ; set eeprom clock low
	dec	10            ; decrement count
	jp	nz,rxloop     ; jump if not done
	ld	9,12          ; move received byte
	pop	12            ; recover consumed registers
	pop	11
	pop	10
	ld	0f6h,#0       ; set port 2 to output mode
	ret                   ; return to caller
sendack:
	ld	2,#0          ; put ack into port 2
	or	0,#1          ; set eeprom clock high
	nop                   ; give some time
	and	0,#0feh       ; set eeprom clock low
	ret                   ; return to caller

