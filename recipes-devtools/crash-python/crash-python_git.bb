SUMMARY = "crash-python is a semantic debugger for the Linux kernel similar to crash debugger"
DESCRIPTION = "crash-python is a semantic debugger for the Linux kernel. It is meant to feel familiar for users \
of the classic crash debugger but allows much more powerful symbolic access to crash dumps as well as enabling an \
API for writing ad-hoc extensions, commands, and analysis scripts."

HOMEPAGE = "https://github.com/crash-python/crash-python"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=eb723b61539feef013de476e68b5c50a"

SRCBRANCH ?= "master"
SRCREV = "284d161905c166504f09788ce5a80eb36f68699d"

PV = "20200329+git${SRCPV}"

SRC_URI = "git://github.com/crash-python/crash-python;branch=${SRCBRANCH} \
           file://0001-crash.types.page-catch-TypeError-for-missing-CONFIG_.patch \
           file://0002-crash.sh-adapted-to-oe-environment.patch \
           file://0003-crash.types.task-work-around-missing-CONFIG_SCHED_IN.patch \
           file://0004-crash.commands.kmem-keep-working-even-if-system-uses.patch \
           file://0001-crash.arch.aarch64-added-support-for-aarch64-archite.patch \
           file://0002-crash.types.percpu-aarch64-does-not-need-pre-cpu-sym.patch \
"

S = "${WORKDIR}/git"

RDEPENDS_${PN} += "bash libkdumpfile python3-pyelftools"
RDEPENDS_${PN}_append_class-target = " gdb"
# for some reason gdb-cross-${TARGET_ARCH} actually converted into gdb-native-${TARGET_ARCH}
# RDEPENDS_${PN}_append_class-native = " gdb-cross-${TARGET_ARCH}"


inherit setuptools3

do_install_append () {
        install -m 755 -d ${D}${datadir}/${BPN}
        install -m 644 ${S}/test-gdb-compatibility.gdbinit ${D}${datadir}/${BPN}/
        install -m 755 -d ${D}${bindir}/
        install -m 755 ${S}/crash.sh ${D}${bindir}/
        ln -sf crash.sh ${D}${bindir}/pycrash
}

COMPATIBLE_HOST = '(x86_64.*)'

BBCLASSEXTEND = "native"