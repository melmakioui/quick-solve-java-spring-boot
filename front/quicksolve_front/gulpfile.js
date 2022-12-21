const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));

function compileSass(){
    return gulp.src('./src/main/resources/templates/scss/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('./src/main/resources/templates/css'));
}

exports.default = compileSass;
