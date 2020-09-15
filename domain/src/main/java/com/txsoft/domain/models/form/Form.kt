package com.txsoft.domain.models.form

import com.txsoft.domain.R
import com.txsoft.domain.models.form.FormType.CONSTRUCTION
import com.txsoft.domain.models.form.FormType.GEOMETRIC


enum class Form(val nameRes: Int, val imageRes: Int, val markedImageRes: Int, val type: FormType) {

    ROUND_BAR(R.string.roundBar, R.drawable.roundbar, R.drawable.roundbar_prof, CONSTRUCTION),
    SQUARE_BAR(R.string.square_bar, R.drawable.square_bar, R.drawable.square_bar_prof, CONSTRUCTION),
    SQUARE_TUBE(R.string.square_tube, R.drawable.square_tube, R.drawable.square_tube_prof, CONSTRUCTION),
    ANGLE(R.string.angle, R.drawable.angle, R.drawable.angle_prof, CONSTRUCTION),
    BEAM(R.string.beam, R.drawable.beam, R.drawable.beam_prof, CONSTRUCTION),
    CHANNEL(R.string.channel, R.drawable.channel, R.drawable.channel_prof, CONSTRUCTION),
    FLAT_BAR(R.string.flat_bar, R.drawable.flat_bar, R.drawable.flat_bar_prof, CONSTRUCTION),
    HEXAGONAL_BAR(R.string.hexag_bar, R.drawable.hexag_bar, R.drawable.hexag_bar_prof, CONSTRUCTION),
    HEXAGONAL_TUBE(R.string.hexag_tube, R.drawable.hexag_tube, R.drawable.hexag_tube_prof, CONSTRUCTION),
    HEXAGONAL_HEX(R.string.hexag_hex, R.drawable.hexag_hex, R.drawable.hexag_hex_prof, CONSTRUCTION),
    PIPE(R.string.pipe, R.drawable.pipe, R.drawable.pipe_prof, CONSTRUCTION),
    T_BAR(R.string.t_bar, R.drawable.t_bar, R.drawable.t_bar_prof, CONSTRUCTION),

    HEXAGONAL_TUBEK(R.string.hexag_tube, R.drawable.hexag_tube, R.drawable.hexag_tube_prof, GEOMETRIC),
    HEXAGONAL_HEXK(R.string.hexag_hex, R.drawable.hexag_hex, R.drawable.hexag_hex_prof, GEOMETRIC),
    PIPEK(R.string.pipe, R.drawable.pipe, R.drawable.pipe_prof, GEOMETRIC),
    T_BARK(R.string.t_bar, R.drawable.t_bar, R.drawable.t_bar_prof, GEOMETRIC),

    DEFAULT(R.string.t_bar, R.drawable.t_bar, R.drawable.t_bar_prof, GEOMETRIC)
}
