package com.txsoft.constructioncalculator.models

import com.txsoft.constructioncalculator.R

enum class Form(
    val nameRes: Int,
    val imageRes: Int,
    val markedImageRes: Int,
    val build: Boolean
) {

    ROUND_BAR(R.string.roundBar, R.drawable.roundbar, R.drawable.roundbar_prof, true),
    SQUARE_BAR(R.string.square_bar, R.drawable.square_bar, R.drawable.square_bar_prof, true),
    SQUARE_TUBE(R.string.square_tube, R.drawable.square_tube, R.drawable.square_tube_prof, true),
    ANGLE(R.string.angle, R.drawable.angle, R.drawable.angle_prof, true),
    BEAM(R.string.beam, R.drawable.beam, R.drawable.beam_prof, true),
    CHANNEL(R.string.channel, R.drawable.channel, R.drawable.channel_prof, true),
    FLAT_BAR(R.string.flat_bar, R.drawable.flat_bar, R.drawable.flat_bar_prof, true),
    HEXAGONAL_BAR(R.string.hexag_bar, R.drawable.hexag_bar, R.drawable.hexag_bar_prof, true),
    HEXAGONAL_TUBE(R.string.hexag_tube, R.drawable.hexag_tube, R.drawable.hexag_tube_prof, true),
    HEXAGONAL_HEX(R.string.hexag_hex, R.drawable.hexag_hex, R.drawable.hexag_hex_prof, true),
    PIPE(R.string.pipe, R.drawable.pipe, R.drawable.pipe_prof, true),
    T_BAR(R.string.t_bar, R.drawable.t_bar, R.drawable.t_bar_prof, true),

    HEXAGONAL_TUBEK(R.string.hexag_tube, R.drawable.hexag_tube, R.drawable.hexag_tube_prof, false),
    HEXAGONAL_HEXK(R.string.hexag_hex, R.drawable.hexag_hex, R.drawable.hexag_hex_prof, false),
    PIPEK(R.string.pipe, R.drawable.pipe, R.drawable.pipe_prof, false),
    T_BARK(R.string.t_bar, R.drawable.t_bar, R.drawable.t_bar_prof, false)
}
