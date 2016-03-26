var gulp = require('gulp');
var less = require('gulp-less');

var lessFiles = 'src/main/resources/static/less/**/*.less';

gulp.task('less', function () {
    gulp.src(lessFiles)
        .pipe(less())
        .pipe(gulp.dest('src/main/resources/static/css'));
});

gulp.task('default', function() {
    gulp.watch(lessFiles, ['less']);
});

gulp.task('build', ['less']);