const gulp = require('gulp');
const sass = require('gulp-sass')(require('sass'));
const cleanCSS = require('gulp-clean-css');

function compileSass(){
    return gulp.src('./src/main/resources/templates/scss/**/*.scss')
        .pipe(sass().on('error', sass.logError))
        .pipe(gulp.dest('./src/main/resources/templates/css'));
}

function minifyCss() {
    return gulp.src('./src/main/resources/templates/css/**/*.css')
        .pipe(cleanCSS({compatibility: 'ie8'}))
        .pipe(gulp.dest('./src/main/resources/templates/dist'));
}

exports.build = gulp.parallel(gulp.series(compileSass,minifyCss));
