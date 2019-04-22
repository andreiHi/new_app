/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 20.04.2019.
 * @version $Id$.
 * @since 0.1.
 */
@org.hibernate.annotations.GenericGenerator(
        name = "ID_GENERATOR",
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = "JPWH_SEQUENCE"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "1000"
                )
        })
package com.app.entities;
