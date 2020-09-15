package com.txsoft.domain.transformation

import com.txsoft.constructioncalculator.models.IForm
import com.txsoft.data.entities.ShapeEntity
import com.txsoft.domain.AdapterData
import com.txsoft.domain.models.Shape

class DataAdapterShape : AdapterData<Shape, ShapeEntity> {
    override fun fromDTO(shape: Shape): ShapeEntity {
        TODO("Not yet implemented")
    }

    override fun fromEntity(entity: ShapeEntity): Shape {
        TODO("Not yet implemented")
    }

    /*  override fun fromDTO(shape: Shape): ShapeEntity {
        when (shape.form) {
            Form.ANGLE -> ShapeEntity()
        }
    }

    override fun fromEntity(entity: ShapeEntity): Shape {
        return object : Shape {
            override var form: IForm
                get() = DataAdapterForm().fromEntity(entity.form)
                set(value) {}
            override var length: Double
                get() = entity.length
                set(value) {}
            override var width: Double?
                get() = if (entity.width == 0.0) null else entity.width
                set(value) {}
            override var height: Double?
                get() = if (entity.height == 0.0) null else entity.height
                set(value) {}
            override var thickness: Double?
                get() = if (entity.thickness == 0.0) null else entity.thickness
                set(value) {}
            override var thickness1: Double?
                get() = if (entity.thickness1 == 0.0) null else entity.thickness1
                set(value) {}
            override var thickness2: Double?
                get() = if (entity.thickness2 == 0.0) null else entity.thickness2
                set(value) {}
            override var diameter: Double?
                get() = if (entity.diameter == 0.0) null else entity.diameter
                set(value) {}
            override var side: Double?
                get() = if (entity.side == 0.0) null else entity.side
                set(value) {}
            override val area: Double
                get() = entity.area
            override val volume: Double
                get() = entity.volume
        }
    }*/
}